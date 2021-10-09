package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wwjie
 * @date 2021/10/4 18:28
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class,args);
    }
}
// 每个业务都对应一个兜底的方法，100个方法就有100个服务降级，
// 会出现代码膨胀问题，我们需要一个统一的fallbackMethod,统一和自定义的分开。

// 每个方法配置一个服务降级方法，造成代码膨胀
//
//   1 : N 除了个别重要核心业务有专属，其他普通的可以通过
//   @DefaultProperties(defaultFallback = “”)
//   统一跳转到统一处理结果页面
//
//  这样通用的和独享的各自分开，避免了代码膨胀，合理减少了代码量。