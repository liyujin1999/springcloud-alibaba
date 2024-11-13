package org.example.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.Pay;
import org.example.entities.PayDTO;
import org.example.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/pay/add")
    public String addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return "成功插入记录，返回值：" + i;
    }

    @DeleteMapping("/pay/del/{id}")
    public int deletePay(@PathVariable("id") Integer id){
        int i = payService.delete(id);
        return i;
    }

    @PutMapping("/pay/update")
    public String updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();

        BeanUtils.copyProperties(payDTO,pay);

        int i = payService.update(pay);
        return "成功修改记录" + i;
    }

    @GetMapping("/pay/get/{id}")
    public Pay getPay(@PathVariable("id") Integer id){
        return payService.getById(id);
    }

    @GetMapping("/pay/all")
    public List<Pay> getAllPay(){
        return payService.getAll();
    }
}
