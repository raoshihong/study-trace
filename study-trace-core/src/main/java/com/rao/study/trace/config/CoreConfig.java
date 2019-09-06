package com.rao.study.trace.config;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableDubbo
@Configuration
@MapperScan(basePackages = {"com.rao.study.trace.mapper"})
@EnableAspectJAutoProxy
public class CoreConfig {
}
