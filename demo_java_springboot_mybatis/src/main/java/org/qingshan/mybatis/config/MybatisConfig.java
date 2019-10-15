package org.qingshan.mybatis.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("dialect", "mysql");
//        pageHelper.setProperties(properties);
//        factory.setPlugins(new Interceptor[]{pageHelper});
        //下划线转驼峰自动映射
        factory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory.getObject();
    }
//
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurerBean() {
//        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//        configurer.setBasePackage("org.qingshan.mybatis.mapper");
//        return configurer;
//    }
}
