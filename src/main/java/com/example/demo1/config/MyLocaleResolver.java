package com.example.demo1.config;


import org.apache.logging.log4j.util.Strings;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//用来国际化

public class MyLocaleResolver implements LocaleResolver {
    //国际化的主要方法
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //请求的参数
        String language = request.getParameter("l");
        Locale locale = Locale.getDefault();      //创建一个默认的locale
        //判断请求参数携带国际化
        if (!StringUtils.isEmpty(language)){

            String[] split = language.split("_");
            //国家，地区
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }


    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
