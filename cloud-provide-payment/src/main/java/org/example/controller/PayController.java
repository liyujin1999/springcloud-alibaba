package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.Pay;
import org.example.entities.PayDTO;
import org.example.resp.ResultData;
import org.example.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法，json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + i);
    }

    @DeleteMapping("/pay/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id){
        int i = payService.delete(id);
        return ResultData.success(i);
    }


    @Operation(summary = "修改", description = "修改支付流水方法")
    @PutMapping("/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();

        BeanUtils.copyProperties(payDTO,pay);

        int i = payService.update(pay);
        return ResultData.success("成功修改记录" + i);
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "获取单个", description = "根据id获取支付流水方法")
    public ResultData<Pay> getPay(@PathVariable("id") Integer id){
        if(id < 0) throw new RuntimeException("id不能为负数");
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping("/pay/all")
    @Operation(summary = "获取全部", description = "获取全部支付流水方法")
    public ResultData<List<Pay>> getAllPay(){
        List<Pay> payList = payService.getAll();
        return ResultData.success(payList);
    }
}
