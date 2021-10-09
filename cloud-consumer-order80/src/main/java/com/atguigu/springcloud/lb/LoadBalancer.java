package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author wwjie
 * @date 2021/10/4 12:44
 */
public interface LoadBalancer {


    //获取Eureka上活着的服务器总数
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);

}
