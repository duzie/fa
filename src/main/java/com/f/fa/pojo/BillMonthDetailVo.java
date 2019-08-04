package com.f.fa.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class BillMonthDetailVo {


    private Date billDate;

    private Double amount;

    private List<BillDetailVo> billDetails;



}
