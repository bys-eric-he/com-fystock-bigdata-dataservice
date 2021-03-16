package com.fystock.bigdata.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fystock.bigdata.cloud.service.PaymentService;
import com.fystock.bigdata.cloud.entity.Payment;
import com.fystock.bigdata.cloud.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/payment")
@Api(value = "/payment", tags = "支付信息")
@RefreshScope
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @SentinelResource("resource")
    @PostMapping("/create")
    @ApiOperation("创建支付记录")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("->插入数据的ID:\t" + payment.getId());
        log.info("->插入结果：" + result);
        if (result > 0) {
            return new CommonResult<>(200, "插入数据成功, 处理服务端口：" + serverPort, result);
        } else {
            return new CommonResult<>(444, "插入数据失败", null);
        }
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation("根据支付ID获取支付记录")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        CommonResult<Payment> result = null;
        try {
            //睡8秒，网关Hystrix3秒超时，会触发熔断降级操作
            Thread.sleep(8000);
            Payment payment = paymentService.getPaymentById(id);
            log.info("***查询结果：" + payment);
            if (payment != null) {
                result = CommonResult.success(payment, "查询数据成功, 处理服务端口：" + serverPort);
            } else {
                result = CommonResult.failed("没有对应记录!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

