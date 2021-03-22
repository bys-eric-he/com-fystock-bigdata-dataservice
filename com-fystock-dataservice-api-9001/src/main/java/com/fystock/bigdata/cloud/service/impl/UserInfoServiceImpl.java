package com.fystock.bigdata.cloud.service.impl;

import com.fystock.bigdata.cloud.dao.UserInfoDao;
import com.fystock.bigdata.cloud.entity.AgeRatioUserInfo;
import com.fystock.bigdata.cloud.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户相关信息业务处理
 *
 * @author He.Yong
 * @since 2021-03-22 17:46:00
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoDao userInfoDao;

    @Autowired
    public UserInfoServiceImpl(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    /**
     * 获取注册用户年龄段占总人数比率
     *
     * @return
     */
    @Override
    public List<AgeRatioUserInfo> findAllAgeRatioUser() {
        return userInfoDao.findAllAgeRatioUser();
    }

    /**
     * 根据导入日期, 获取注册用户年龄段占总人数比率
     *
     * @param importDateTime
     * @return
     */
    @Override
    public List<AgeRatioUserInfo> findAllAgeRatioUserByImportDateTime(String importDateTime) {
        return userInfoDao.findAllAgeRatioUserByImportDateTime(importDateTime);
    }
}
