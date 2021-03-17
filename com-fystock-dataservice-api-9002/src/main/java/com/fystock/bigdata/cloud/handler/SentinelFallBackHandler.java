package com.fystock.bigdata.cloud.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * 限流异常回调处理器,统一处理这样的降级
 *
 * @author He.Yong
 * @since 2021-03-17 11:11:00
 */
@Slf4j
public class SentinelFallBackHandler {
    /**
     * fallback函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass为对应的类的 Class 对象，
     * 注意对应的函数必需为 static函数，否则无法解析。 defaultFallback（since 1.6.0）：默认的 fallback函数名称，可选项，
     * 通常用于通用的 fallback逻辑（即可以用于很多服务或方法）。默认 fallback函数可以针对所有类型的异常（除了exceptionsToIgnore里面排除掉的异常类型）进行处理。若同时配置了 fallback和 defaultFallback，则只有 fallback会生效。defaultFallback函数签名要求：返回值类型必须与原函数返回值类型一致；
     *
     * @param id 支付ID
     * @param t  抛出的异常
     */
    public static String paymentFallbackHandler(Long id, Throwable t) {
        log.error("-------->支付订单限流异常回调处理 ID：" + id + "异常信息：" + t.getMessage());
        return "-------->支付订单限流异常回调处理 ID：" + id + "异常信息：" + t.getMessage();
    }
}
