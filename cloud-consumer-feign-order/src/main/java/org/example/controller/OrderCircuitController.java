package org.example.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.example.apis.PayFeignApi;
import org.example.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCircuitController {
    @Autowired
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallBack")
    public ResultData myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.circuit(id);
    }

    //myCircuitFallback就是服务降级后的兜底处理方法
    public ResultData myCircuitFallBack(Integer id, Throwable throwable) {
        return ResultData.success("系统繁忙，请稍后重试");
    }

    /**
     *(船的)舱壁,隔离
     * @param id
     * @return
     */
    /*@GetMapping(value = "/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback",type = Bulkhead.Type.SEMAPHORE)
    public String myBulkhead(@PathVariable("id") Integer id)
    {
        return payFeignApi.myBulkhead(id);
    }
    public String myBulkheadFallback(Throwable t)
    {
        return "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
    }*/

    /**
     * (船的)舱壁,隔离,THREADPOOL
     * @param id
     * @return
     */
    @GetMapping(value = "/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadPoolFallback",type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadTHREADPOOL(@PathVariable("id") Integer id)
    {
        System.out.println(Thread.currentThread().getName()+"\t"+"开始进入方法!!!");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"退出方法!!!");

        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + " Bulkhead.Type.THREADPOOL");
    }
    public CompletableFuture<String> myBulkheadPoolFallback(Integer id,Throwable t)
    {
        return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
    }

    @GetMapping(value = "/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service",fallbackMethod = "myRatelimitFallback")
    public String myBulkhead(@PathVariable("id") Integer id)
    {
        return payFeignApi.myRatelimit(id);
    }
    public String myRatelimitFallback(Integer id,Throwable t)
    {
        return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
    }
}
