///**
// * <p>Title: EncryptPropertyPlaceholderConfigurer.java</p>
// * <p>Copyright: Copyright (c) 2017 - </p>
// */
//package org.qingshan.mybatis.config;
//
//import org.qingshan.mybatis.util.AESUtils;
//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//
///*其实这个文件不起作用
//原因:当我们使用spring boot后，对数据库相关配置参数解析后，数据库自动初始化装配无法成功，即数据库会在PropertyPlaceholderConfigurer类之前初始化，如果加密内容正好是数据库连接密码，那么程序启动后会因为数据库无法连接而报错，程序自动挂断。错误内容：Failed to initialize pool: Access denied for user 'root'@'localhost' (using password: YES)*/
////也就是说在进行属性值转换之前，数据库自动装配已经启动，但这个时候取到的密码仍然是加密的密码。但是如果是用配置文件配置数据源则是有效的
//@Configuration
//public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
//
//
//    /**
//     * 解密指定propertyName的加密属性值
//     *
//     * @param propertyName
//     * @param propertyValue
//     * @return
//     */
//    @Override
//    protected String convertProperty(String propertyName, String propertyValue) {
//        if (propertyName.contains("password")) {
//            return AESUtils.encode(propertyValue);
//        }
//        return super.convertProperty(propertyName, propertyValue);
//    }
//
//    @Bean
//    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
//        PropertyPlaceholderConfigurer placeholderConfigurer = new EncryptPropertyPlaceholderConfigurer();
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource resource = resolver.getResource("classpath:application.properties");
//        placeholderConfigurer.setLocation(resource);
//        return placeholderConfigurer;
//    }
//}
