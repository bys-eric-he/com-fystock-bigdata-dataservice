package com.fystock.bigdata.cloud.service;

import com.fystock.bigdata.cloud.entity.AgeRatioUserInfo;

import java.util.List;

/**
 * 用户相关信息业务处理
 *
 * @author He.Yong
 * @since 2021-03-22 17:46:00
 */
public interface UserInfoService {
    /**
     * 获取注册用户年龄段占总人数比率
     *
     * @return
     */
    List<AgeRatioUserInfo> findAllAgeRatioUser();

    /**
     * 根据导入日期, 获取注册用户年龄段占总人数比率
     *
     * @param importDateTime
     * @return
     */
    List<AgeRatioUserInfo> findAllAgeRatioUserByImportDateTime(String importDateTime);
}
