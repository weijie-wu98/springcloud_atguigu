package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wwjie
 * @date 2021/10/4 18:30
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id);


}
//现在客户端与服务端关系紧紧耦合，客户端能跑是因为接口调用了微服务的业务逻辑方法，
// 我们如果针对客户端接口做一些处理，把它调用的所有微服务方法进行降级，就可以解决耦合问题。

//这个案例服务降级处理是在客户端80完成的，与服务端8001没有关系，
// 只需要为 Feign 客户端定义的接口添加一个服务降级处理的实现类即可实现解耦。