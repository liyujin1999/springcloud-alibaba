package org.example.apis;

import org.example.config.FeignConfiguration;
import org.example.entity.PayDTO;
import org.example.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloud-payment-service", configuration = FeignConfiguration.class)
//@FeignClient(value = "cloud-gateway", configuration = FeignConfiguration.class)
public interface PayFeignApi {

    //新增一条支付记录
    @PostMapping("/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);

    //获取单条记录
    @GetMapping("/pay/get/{id}")
    public ResultData getPay(@PathVariable("id") Integer id);

    @GetMapping("/pay/get/info")
    public String mylb();

    @GetMapping("/pay/circuit/{id}")
    public ResultData circuit(@PathVariable("id") Integer id);

    /**
     * Resilience4j Bulkhead 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4j Ratelimit 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);

    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例01
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     * @return
     */
    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();
}
