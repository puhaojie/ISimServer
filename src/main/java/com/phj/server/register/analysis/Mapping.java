package com.phj.server.register.analysis;

import java.util.ArrayList;
import java.util.List;

/*
 <servlet-mapping>
         <servlet-name>login</servlet-name>
         <url-pattern>/login</url-pattern>
     </servlet-mapping>
 */
public class Mapping {
    private String name;
    private List<String> urlPattern = new ArrayList<>(2);

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public List<String> getUrlPattern() {
        return urlPattern;
    }

    void add(String pattern) {
        this.urlPattern.add(pattern);
    }



}
