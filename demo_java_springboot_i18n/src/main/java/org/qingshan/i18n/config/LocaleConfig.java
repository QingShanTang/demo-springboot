package org.qingshan.i18n.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleConfig {

    /**
     * 默认解析器
     * springboot内部默认找这个bean。
     * 国际化文件使用策略:如果没有指定默认语言,则根据用户使用浏览器的语言使用国际化文件。
     * 本质上国际化语言的控制是LocaleResolver bean中Locale的设置
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver lr = new SessionLocaleResolver();
        lr.setDefaultLocale(Locale.US);//默认语言
//        lr.setDefaultLocale(null);  //如果locale是null的话,根据用户浏览器语言使用文件
        return lr;
    }


}
