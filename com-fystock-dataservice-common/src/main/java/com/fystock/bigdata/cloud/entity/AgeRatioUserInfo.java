package com.fystock.bigdata.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgeRatioUserInfo extends BaseInfo {
    /**
     * 主键ID 添加一个空的ID标识，因为JPA在映射实体是需要一个ID，在这里将age字段作为虚拟主键。
     */
    private String age;
    /**
     * 注册总人数
     */
    private Integer totalCount;
    /**
     * 年龄段总人数
     */
    private Integer ageCount;
    /**
     * 该年龄段占注册总人数比率(%)
     */
    private BigDecimal ratio;
}
