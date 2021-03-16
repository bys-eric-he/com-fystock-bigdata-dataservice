package com.fystock.bigdata.cloud.dao;

import com.fystock.bigdata.cloud.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectByUserId(Integer userId);
    User selectByUserName(String userName);
}