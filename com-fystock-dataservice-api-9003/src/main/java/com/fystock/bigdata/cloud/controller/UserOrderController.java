package com.fystock.bigdata.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fystock.bigdata.cloud.entity.UserOrder;
import com.fystock.bigdata.cloud.handler.SentinelBlockHandler;
import com.fystock.bigdata.cloud.handler.SentinelFallBackHandler;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.UserOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "/api/market/v1/user-order", tags = "用户订单API服务")
@RestController
@RequestMapping("/api/market/v1/user-order")
public class UserOrderController {
    @Value("${server.port}")
    private String serverPort;

    @Resource
    @Qualifier("userOrderService")
    private UserOrderService userOrderService;

    @ApiOperation("插入用户订单")
    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public CommonResult<Object> upInsertUserOrder(UserOrder userOrder) throws Exception {
        int result = userOrderService.upInsertUserOrder(userOrder);
        return CommonResult.success(result, "插入数据成功, 处理服务端口：" + serverPort);
    }

    @SentinelResource(value = "userOrderService",
            fallbackClass = SentinelFallBackHandler.class, fallback = "userOrderFallbackHandler",
            blockHandlerClass = SentinelBlockHandler.class, blockHandler = "exceptionHandler")
    @ApiOperation("获取所有订单列表")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public CommonResult<List<UserOrder>> getAllOrders() {
        List<UserOrder> userOrders = userOrderService.getAllOrders();

        return CommonResult.success(userOrders, "查询数据成功, 处理服务端口：" + serverPort);
    }

    @ApiOperation("获取指定订单信息")
    @RequestMapping(path = "/getByOrder/{orderId}", method = RequestMethod.GET)
    public CommonResult<UserOrder> getOrderById(@PathVariable String orderId) {
        UserOrder userOrder = userOrderService.getOrderById(orderId);
        return CommonResult.success(userOrder, "查询数据成功, 处理服务端口：" + serverPort);

    }

    @ApiOperation("获取指定用户订单列表")
    @RequestMapping(path = "/getByUser/{userId}", method = RequestMethod.GET)
    public CommonResult<List<UserOrder>> getOrderByUserId(@PathVariable String userId) {
        List<UserOrder> userOrders = userOrderService.getOrdersByUserId(userId);
        return CommonResult.success(userOrders, "查询数据成功, 处理服务端口：" + serverPort);

    }
}
