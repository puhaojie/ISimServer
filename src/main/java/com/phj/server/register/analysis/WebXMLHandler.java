package com.phj.server.register.analysis;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 解析注册XML的类
 * Default base class for SAX2 to analysis handlers.
 */
public class WebXMLHandler extends DefaultHandler {

    // 两个集合
    private List<Entity> entityList;
    private List<Mapping> mappingList;

    // 用于临时保存数据的对象
    private Entity entityTemp;
    private Mapping mappingTemp;

    // 开始tag
    private String beginTag;
    // 当前是map否
    private boolean isMap;


    private final static String SERVLET= "servlet";
    private final static String SERVLET_MAPPING= "servlet-mapping";
    private final static String URL_PATTERN= "url-pattern";
    private final static String SERVLET_NAME= "servlet-name";
    private final static String SERVLET_CLASS= "servlet-class";

    @Override
    public void startDocument()  {
        //文档解析开始
        entityList = new ArrayList<>();
        mappingList = new ArrayList<>();

    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        //开始元素
        if (null != qName) {
            beginTag = qName;
            if (qName.equals(SERVLET)) {
                isMap = false;
                entityTemp = new Entity();
            } else if (qName.equals(SERVLET_MAPPING)) {
                isMap = true;
                mappingTemp = new Mapping();
            }

        }

    }


    @Override
    public void characters(char[] ch, int start, int length) {
        //处理内容
        if (null != beginTag) {
            String str = new String(ch, start, length);
            if (isMap) {
                if (beginTag.equals(SERVLET_NAME)) {
                    mappingTemp.setName(str);
                } else if (beginTag.equals(URL_PATTERN)) {
                    mappingTemp.add(str);
                }
            } else {
                if (beginTag.equals(SERVLET_NAME)) {
                    entityTemp.setName(str);
                } else if (beginTag.equals(SERVLET_CLASS)) {
                    entityTemp.setClz(str);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        //结束元素
        if (null != qName) {

            if (qName.equals(SERVLET)) {
                entityList.add(entityTemp);
            } else if (qName.equals(SERVLET_MAPPING)) {
                mappingList.add(mappingTemp);
            }

        }
        beginTag = null;
    }


    @Override
    public void endDocument() {
        //文档解析结束
    }

    public List<Entity> getEntityList() {
        return entityList;
    }


    public List<Mapping> getMappingList() {
        return mappingList;
    }


}

