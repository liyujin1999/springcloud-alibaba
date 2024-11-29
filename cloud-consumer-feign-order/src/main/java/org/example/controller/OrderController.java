package org.example.controller;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.apis.PayFeignApi;
import org.example.entity.PayDTO;
import org.example.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        ResultData resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }

    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        log.info("开始调用/feign/pay/get/{id}接口");
        ResultData resultData = null;
        try{
            System.out.println("调用开始： " + DateUtil.now());
            resultData = payFeignApi.getPay(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束： " + DateUtil.now());
        }

        return resultData;
    }

    /**
     * openfeign天然支持负载均衡演示
     *
     * @return
     */
    @GetMapping("/feign/pay/get/info")
    public String mylb(){

        return payFeignApi.mylb();
    }


}
