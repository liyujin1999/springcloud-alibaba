package org.example.controller;

import org.example.entity.PayDTO;
import org.example.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    public  static final String PaymentSru_URL = "http://localhost:8001";//先写死

    @Autowired
    private RestTemplate restTemplate;

    //新增
    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
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
