package org.qingshan.i18n.config;

import org.qingshan.i18n.interceptor.LanguageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getLanguageInterceptor()).addPathPatterns("/hello");
        registry.addInterceptor(getLocaleChangeInterceptor()).addPathPatterns("/hello");
    }

    //使用自定义语言拦截器
    @Bean
    public LanguageInterceptor getLanguageInterceptor() {
        return new LanguageInterceptor();
    }

    //使用spring自带语言拦截器
    @Bean
    public LocaleChangeInterceptor getLocaleChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");  //http://localhost:8888/hello?lang=en_US
        return lci;
    }

}
