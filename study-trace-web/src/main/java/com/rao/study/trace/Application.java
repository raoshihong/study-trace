package com.rao.study.trace;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableDubbo
@SpringBootApplication
@ComponentScan(
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = "com.rao.study.trace.lib.*"
        )}
)
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
