package com.wis.template.infrastructure.persistent.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import com.wis.template.infrastructure.persistent.dao.BaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackageClasses = BaseMapper.class)
@EnableTransactionManagement
public class MyBatisPlusConfiguration {

    @Value("${mybatis-plus.dialectType:}")
    private String dialectType;

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        if (StringUtils.isNotBlank(dialectType)) {
            interceptor.setDialectType(dialectType);
        }
        return interceptor;
    }

//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        //格式化sql语句
//        Properties properties = new Properties();
//        properties.setProperty("format", "false");
//        performanceInterceptor.setProperties(properties);
//        return performanceInterceptor;
//    }

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}
