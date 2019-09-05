package com.rao.study.trace.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.rao.study.trace.mapper"})
public class CoreConfig {
}
