package com.f.fa.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Bill {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 账单名称
     */
    private String name;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 期数
     */
    private Integer periods;

    /**
     * 周期
     */
    private Integer cycle;

    /**
     * 开始日期
     */
    private Date startDate;
}