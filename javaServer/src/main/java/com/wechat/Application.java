package com.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Nelson on 2020/6/5.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application  extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 工程打成war需要继承SpringBootServletInitializer，重写configure方法
     * @date 2018年12月7日
     * @author zxp
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WechatApplication.class);
    }
}
