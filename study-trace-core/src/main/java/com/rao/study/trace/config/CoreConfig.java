package com.rao.study.trace.config;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableDubbo
@Configuration
@MapperScan(basePackages = {"com.rao.study.trace.mapper"})
@ComponentScan(basePackages = {"com.rao.study.trace.*"})//开启加密配置
@EnableAspectJAutoProxy
public class CoreConfig {
}
