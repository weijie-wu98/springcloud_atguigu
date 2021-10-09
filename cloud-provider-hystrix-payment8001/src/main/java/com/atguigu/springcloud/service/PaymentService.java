package com.atguigu.springcloud.service;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author wwjie
 * @date 2021/10/4 17:46
 */
@Service
public class PaymentService {
    /**
     * 正常访问，肯定OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池:  " + Thread.currentThread().getName() + "  paymentInfo_OK,id:  " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * http://localhost:8001/payment/hystrix/timeout/31
     * @HystrixCommand报异常后如何处理：
     * 一旦调用服务方法失败并抛出了错误信息后，
     * 会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
     *
     * @param id
     * @return
     */
@HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
})
    public String paymentInfo_Timeout(Integer id){
        int timeNumber = 3;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName() + "  paymentInfo_Timeout,id:  " + id + "\t" + "耗时(S):"+timeNumber;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+" 系统繁忙，请稍后再试"+id+"\t";
    }
//    当前服务不可用了，做服务降级，兜底的方案都是paymentInfo_TimeOutHandler


    /**
     * =====服务熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreak_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"),


    })
    public String paymentCircuitBreak(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("-----id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();   //等价于UUID.randomUUID()
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号"+serialNumber;
    }
    public String paymentCircuitBreak_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能为负数，请稍后再试 id："+id;
    }
//Controller 调用 Service，请求数10次内出现6次以上错误，就会触发熔断，
// 熔断后正确的请求也不能立刻被响应，而是缓慢、正确率高了才响应。


}
