package org.example.apis;

import org.example.entity.PayDTO;
import org.example.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {

    //新增一条支付记录
    @PostMapping("/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);

    //获取单条记录
    @GetMapping("/pay/get/{id}")
    public ResultData getPay(@PathVariable("id") Integer id);

    @GetMapping("/pay/get/info")
    public String mylb();
}
