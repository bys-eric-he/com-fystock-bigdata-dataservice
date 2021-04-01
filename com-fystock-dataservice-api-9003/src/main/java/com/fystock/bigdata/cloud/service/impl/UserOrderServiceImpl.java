package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.entity.UserOrder;
import com.fystock.bigdata.cloud.mapper.UserOrderMapper;
import com.fystock.bigdata.cloud.service.UserOrderService;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("userOrderService")
/*@CacheConfig(cacheNames = "USER-ORDER") 代替 @CacheEvict(value = "UserOrderService"）*/
@CacheConfig(cacheNames = "USER-ORDER")
public class UserOrderServiceImpl implements UserOrderService {

    @Resource
    private UserOrderMapper userOrderMapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 插入用户订单
     * <p>
     * 1. @CacheEvict是用来标注在需要清除缓存元素的方法或类上的。
     * 当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。
     * 2. allEntries = true
     * allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。
     * 当指定了allEntries为true时，清除缓存中的所有元素，Spring Cache将忽略指定的key。有的时候我们需要Cache一下清除所有的元素，
     * 这比一个一个清除元素更有效率。
     * 3. beforeInvocation=true
     * 清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。
     * 使用beforeInvocation可以改变触发清除操作的时间，当我们指定该属性值为true时，Spring会在调用该方法之前清除缓存中的指定元素。
     *
     * @param userOrder
     * @return
     */
    @CacheEvict(/*value = "UserOrderService",*/ key = "'getAllOrders:all'", allEntries = true, beforeInvocation = true)
    @Override
    public int upInsertUserOrder(UserOrder userOrder) throws Exception {
        log.info("-----------------持久化消息到用户订单表-------------------");
        log.info(objectMapper.writeValueAsString(userOrder));
        return userOrderMapper.upInsertUserOrder(userOrder);
    }

    /**
     * 删除指定订单
     *
     * @param id
     * @return
     */
    @CacheEvict(/*value = "UserOrderService,"*/ key = "'getOrderById:' + #id")
    @Override
    public int deleteUserOrder(String id) {
        return userOrderMapper.deleteUserOrder(id);
    }

    /**
     * 获取指定订单信息
     *
     * @param id
     * @return
     */
    @Cacheable(/*value = "UserOrderService",*/ key = "'getOrderById:' + #id", condition = "#id !='0'")
    @Override
    public UserOrder getOrderById(String id) {
        return userOrderMapper.getOrderById(id);
    }

    /**
     * 获取指定用户订单
     *
     * @param userId
     * @return
     */
    @Cacheable(/*value = "UserOrderService",*/ key = "'getOrderByUserId:' + #userId", condition = "#userId !='0'")
    @Override
    public List<UserOrder> getOrdersByUserId(String userId) {
        return userOrderMapper.getOrdersByUserId(userId);
    }

    /**
     * 获取所有订单信息
     *
     * @return
     */
    @Cacheable(/*value = "UserOrderService",*/ key = "'getAllOrders:all'")
    @Override
    public List<UserOrder> getAllOrders() {
        return userOrderMapper.getAllOrders();
    }
}
