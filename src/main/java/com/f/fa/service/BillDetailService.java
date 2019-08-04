package com.f.fa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.f.fa.pojo.BillDetail;
import com.f.fa.pojo.BillDetailVo;

import java.util.List;

public interface BillDetailService extends IService<BillDetail> {


    List<BillDetailVo> findBillDetails();

}
