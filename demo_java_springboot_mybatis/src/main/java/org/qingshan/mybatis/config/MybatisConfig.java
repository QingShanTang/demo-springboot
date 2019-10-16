package org.qingshan.mybatis.config;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class MybatisConfig {

    /*
    参考：https://blog.csdn.net/u013057846/article/details/83005134
    其实当配置数据源的时候，mybatis的stater已经默认创建了一个SqlSessionFactory，不需要再手动配置了。
    如果手动配置，那么springboot配置文件中关于mybatis的配置将不再生效，要在手动配置的地方配置，如mapper-locations，type-aliases-package。
    如果需要一些特定的配置，比如下划线转驼峰自动映射这些在配置文件中无法配置的时候，那么就必须手动配置*/
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);

//        factory.setTypeAliasesPackage("org.qingshan.mybatis.mapper");
//        如果java中mapper映射类和resources的映射文件路径一致时，系统默认不需要配置。反之，则需要手动配置映射文件位置。
//        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:org.qingshan.mybatis.mapper/*.xml"));

        //sql中下划线转驼峰自动映射
        factory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory.getObject();
    }


    /*    映射类的配置有三种方式：
        一，在配置文件中用 mybatis.type-aliases-package 配置
        二,在手动配置SqlSessionFactory的时候用 factory.setTypeAliasesPackage
        三，就是依靠MapperScannerConfigurer配置，如下，如果多个路径可以用逗号相隔。
        其中第一，第二种方式需要在映射类的上面加个@Mapper注解，第三种不需要。
        当代码是generator自动生成时，是没有这个注解的，所以项目中还是使用第三种比较方便*/
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurerBean() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("org.qingshan.mybatis.mapper,org.qingshan.mybatis.dao");
        return configurer;
    }


    /**
     * 分页工具pageHelper配置
     */
    @Configuration
    public class PageHelperConfig {
        @Bean
        public PageHelper getPageHelper() {
            PageHelper pageHelper = new PageHelper();
            Properties properties = new Properties();
            properties.setProperty("helperDialect", "mysql");
            properties.setProperty("reasonable", "true");
            properties.setProperty("supportMethodsArguments", "true");
            properties.setProperty("params", "count=countSql");
            pageHelper.setProperties(properties);
            return pageHelper;
        }
    }
}
