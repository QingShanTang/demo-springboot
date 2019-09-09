package org.qingshan.i18n.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//使用自定义拦截器是为了更自由的定义语言选择逻辑,如果语言需要后台自己设置,则需使用自定义拦截器。
// 如果是前台设置,使用本地变更拦截器LocaleChangeInterceptor会更方方便
public class LanguageInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LocaleResolver localeResolver;


    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Locale locale = getLocale("zh");
        localeResolver.setLocale(request, response, locale);
        return true;
    }

    /**
     * 根据系统language获取locale
     */
    private Locale getLocale(
            String language
    ) {
        Locale locale = new Locale("en", "US");
        //根据系统处理逻辑
        if (null != language && "zh".equals(language)) {
            locale = new Locale("zh", "CN");
        }
        return locale;
    }
}
