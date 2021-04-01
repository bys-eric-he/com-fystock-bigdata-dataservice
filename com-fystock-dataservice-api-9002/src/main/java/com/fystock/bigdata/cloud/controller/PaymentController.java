package com.fystock.bigdata.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fystock.bigdata.cloud.handler.SentinelBlockHandler;
import com.fystock.bigdata.cloud.handler.SentinelFallBackHandler;
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
@RequestMapping("/api/data/v1/payment")
@Api(value = "/api/data/v1/payment", tags = "支付信息")
@RefreshScope
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

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

    /**
     * fallback：若本接口出现未知异常，则调用fallback指定的接口。
     * blockHandler：若本次访问被限流或服务降级，则调用blockHandler指定的接口。
     * 使用 SpringCloud Alibaba 的Sentinel 时，在 Sentinel 控制台，进行流控规则 配置 时，
     * 资源名 有两种填写方式：
     * 1.请求的 URL (对应的 @GetMapping 、RequestMapping 的 value 值)
     * 2.@SentinelResource 的 value 值
     *
     * @param id
     * @return
     */
    @SentinelResource(value = "paymentService",
            fallbackClass = SentinelFallBackHandler.class, fallback = "paymentFallbackHandler",
            blockHandlerClass = SentinelBlockHandler.class, blockHandler = "exceptionHandler")
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation("根据支付ID获取支付记录")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***查询结果：" + payment);
        if (payment != null) {
            return CommonResult.success(payment, "查询数据成功, 处理服务端口：" + serverPort);
        } else {
            return CommonResult.failed("没有对应记录!");
        }
    }
}
