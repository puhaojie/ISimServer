package com.phj.server.register.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.phj.server.handle.Servlet;
import com.phj.server.model.servlet.ServletConfig;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Analysis {

    // /register -> register
    private final static Map<String, String> mServletMapingMap = new HashMap<>();
    // register -> Register.class
    private final static Map<String, String> mServletMap = new HashMap<>();

    // key = 类路径, value = object
    private final static Map<String, Servlet> mServletValueMap = new HashMap<>();

    // 待解析的XML文件路径
    private final static String rootFileName = "D:\\F\\project\\self\\web\\ISimServer\\src\\main\\resources\\web.xml";

    /**
     * 开始分析XML
     */
    public static void startAnalysis() throws ParserConfigurationException, SAXException, IOException {
        // 1、文件处理
        File rootFile = new File(rootFileName);
        if (!rootFile.exists()) {
            throw new IllegalArgumentException("analysis's file not found!");
        }

        // 2、开始解析文件，存入到相关容器
        //获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //获取解析器
        SAXParser sax = factory.newSAXParser();
        //指定xml+处理器
        WebXMLHandler web = new WebXMLHandler();
        InputStream inputStream = new FileInputStream(rootFile);
        sax.parse(inputStream, web);
        inputStream.close();

        // 保存到内存中
        //servlet-name  servlet-class
        for (Entity entity : web.getEntityList()) {
            mServletMap.put(entity.getName(), entity.getClz());
        }

        //url-pattern servlet-name
        for (Mapping maping : web.getMappingList()) {
            List<String> urls = maping.getUrlPattern();
            for (String url : urls) {
                mServletMapingMap.put(url, maping.getName());
            }
        }
    }

    /**
     * 根据地址返回当前的处理类
     * @param url 路径URL
     * @return Servlet对象
     */
    public Servlet getServletByUrl(String url) {
        if (url == null || Objects.equals("",url)) {
            return null;
        }
        String pattern = mServletMapingMap.get(url);

        if (pattern == null || Objects.equals("",pattern)) {
            return null;
        }

        return getServletByPattern(pattern);
    }


    /**
     * 根据Pattern返回当前的处理类
     * @param pattern 路径URL
     * @return Servlet对象
     */
    private Servlet getServletByPattern(String pattern) {
        if (pattern == null || Objects.equals("",pattern)) {
            return null;
        }

        // 内存中有没有
        String clz = mServletMap.get(pattern);
        if (clz == null) {
            return null;
        }

        Servlet servletValue = mServletValueMap.get(clz);
        if (servletValue == null) {
            // 初始化
            try {
                servletValue = (Servlet) Class.forName(clz).newInstance();
                mServletValueMap.put(clz,servletValue);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (servletValue != null) {
            servletValue.init(new ServletConfig(clz));
        }
        return servletValue;

    }


    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Analysis.startAnalysis();
    }
}
