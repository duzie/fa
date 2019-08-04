package com.f.fa.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class BillDetailVo {


    private Date billDate;

    private Double amount;

    private List<BillDetail> billDetails;

}
