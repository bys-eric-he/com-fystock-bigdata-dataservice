package com.fystock.bigdata.cloud.mapper;

import com.fystock.bigdata.cloud.entity.UserOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserOrderMapper {
    /**
     * 插入用户订单
     *
     * @param entity
     * @return
     */
    int upInsertUserOrder(UserOrder entity);

    /**
     * 删除指定订单
     *
     * @param id
     * @return
     */
    int deleteUserOrder(String id);

    /**
     * 获取指定订单信息
     *
     * @param id
     * @return
     */
    UserOrder getOrderById(String id);

    /**
     * 获取指定用户订单
     *
     * @param userId
     * @return
     */
    List<UserOrder> getOrdersByUserId(String userId);

    /**
     * 获取所有订单信息
     *
     * @return
     */
    List<UserOrder> getAllOrders();
}
