package com.fystock.bigdata.cloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fystock.bigdata.cloud.entity.Payment;
import com.fystock.bigdata.cloud.response.CommonResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 若本次访问被限流或服务降级，则调用blockHandler指定的接口。
 */
@Slf4j
public class SentinelBlockHandler {
    /**
     * 方法一定要是静态方法，否则无法解析，调用时会报500
     * 同时返回值此要保持和调用的方法一致
     *
     * @param id 业务ID
     * @param ex 异常信息
     */
    public static CommonResult<Payment> exceptionHandler(Long id, BlockException ex) {
        log.error("-------->支付订单查询限流! BlockHandler回调处理 ID：" + id + "BlockException异常信息：" + ex.getMessage());
        return CommonResult.failed("-------->支付订单查询限流! BlockHandler异常回调处理 ID：" + id + "BlockException异常信息：" + ex.getMessage());
    }
}