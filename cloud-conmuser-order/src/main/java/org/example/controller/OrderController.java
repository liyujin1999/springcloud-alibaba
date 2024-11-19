package org.example.controller;

import org.example.entity.PayDTO;
import org.example.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

//    public  static final String PaymentSru_URL = "http://localhost:8001";//先写死

    public static final String PaymentSru_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    //新增
    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        System.out.println("当前微服务端口号: " + environment.getProperty("server.port"));
        return restTemplate.postForObject(PaymentSru_URL + "/pay/add", payDTO, ResultData.class);
    }

    //获取
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSru_URL + "/pay/get/" + id, ResultData.class, id);
    }


    //修改


    //删除


}
