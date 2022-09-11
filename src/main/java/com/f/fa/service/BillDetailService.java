package com.f.fa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.f.fa.pojo.BillDetail;
import com.f.fa.pojo.BillMonthDetailVo;

import java.util.List;

public interface BillDetailService extends IService<BillDetail> {


    List<BillMonthDetailVo> findBillDetails(int balance);

}
