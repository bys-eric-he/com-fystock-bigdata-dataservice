package com.fystock.bigdata.cloud.controller;

import cn.hutool.json.JSONObject;
import com.fystock.bigdata.cloud.dao.UserMapper;
import com.fystock.bigdata.cloud.entity.User;
import com.fystock.bigdata.cloud.response.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    public UserMapper userMapper;

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/getById/{userId}")
    public CommonResult<User> getById(@PathVariable Integer userId) {
        User user = userMapper.selectByUserId(userId);
        return CommonResult.success(user);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    @GetMapping("/getByName/{userName}")
    public CommonResult<User> getByName(@PathVariable String userName) {
        User user = userMapper.selectByUserName(userName);
        return CommonResult.success(user);
    }

    /**
     * 获取授权的用户信息
     *
     * @return 授权信息
     */
    @GetMapping("/current/get")
    public CommonResult<JSONObject> user() {
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(userStr);
        return CommonResult.success(userJsonObject);
    }
}