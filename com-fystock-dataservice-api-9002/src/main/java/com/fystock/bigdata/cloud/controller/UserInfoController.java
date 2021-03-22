package com.fystock.bigdata.cloud.controller;

import com.fystock.bigdata.cloud.entity.AgeRatioUserInfo;
import com.fystock.bigdata.cloud.response.CommonResult;
import com.fystock.bigdata.cloud.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Api(value = "/api/v1/user_info", tags = "注册用户数据分析统计结果")
@RequestMapping(value = "/api/v1/user_info")
public class UserInfoController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取注册用户年龄段占总人数比率
     *
     * @return
     */
    @ApiOperation("获取注册用户年龄段占总人数比率")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/age_ratio_user", method = RequestMethod.GET)
    public CommonResult<List<AgeRatioUserInfo>> findAllAgeRatioUserByImportDateTime() {
        List<AgeRatioUserInfo> ageRatioUserInfoModels = userInfoService.findAllAgeRatioUser();
        log.info("***查询结果：" + ageRatioUserInfoModels);
        return CommonResult.success(ageRatioUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }

    /**
     * 根据导入日期获取注册用户年龄段占总人数比率
     *
     * @param importDateTime
     * @return
     */
    @ApiOperation("根据导入日期获取注册用户年龄段占总人数比率")
    @PreAuthorize("hasAnyAuthority('FYSTOCK')")
    @RequestMapping(value = "/age_ratio_user/{importDateTime}", method = RequestMethod.GET)
    public CommonResult<List<AgeRatioUserInfo>> findAllAgeRatioUserByImportDateTime(@PathVariable String importDateTime) {
        List<AgeRatioUserInfo> ageRatioUserInfoModels = userInfoService.findAllAgeRatioUserByImportDateTime(importDateTime);
        log.info("***查询结果：" + ageRatioUserInfoModels);
        return CommonResult.success(ageRatioUserInfoModels, "查询数据成功, 处理服务端口：" + serverPort);
    }
}
