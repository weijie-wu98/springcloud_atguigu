package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author wwjie
 * @date 2021/10/7 21:58
 */
@Component   //注解
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "PaymentFallback Service fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }
}
