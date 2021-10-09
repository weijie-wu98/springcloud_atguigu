package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wwjie
 * @date 2021/9/25 23:34
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //使用@LodBalanced注解赋予RestTemplate负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

/**发现报错了，现在对外暴露的不再是地址和端口，只认微服务名称了
 可是微服务并不知道下面有几个，找不到这个主机名称
 需要使用#LoadBalanced注解开启RestTemplate负载均衡功能
 提前说一下：这个就是后面要介绍的Ribbon负载均衡功能。
 */
//这样Ribbon和Eureka整合后Consumer可以直接调用服务而不用再关心地址和端口号，
// 且该服务还有负载均衡功能了。