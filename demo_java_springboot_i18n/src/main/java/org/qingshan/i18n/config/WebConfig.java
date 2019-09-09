package org.qingshan.i18n.config;

import org.qingshan.i18n.interceptor.LanguageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLanguageInterceptor()).addPathPatterns("/hello");
    }

    @Bean
    public LanguageInterceptor getLanguageInterceptor() {
        return new LanguageInterceptor();
    }

}
