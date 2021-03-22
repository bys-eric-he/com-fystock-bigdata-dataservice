package com.fystock.bigdata.cloud.dao;

import com.fystock.bigdata.cloud.entity.AgeRatioUserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoDao {
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
