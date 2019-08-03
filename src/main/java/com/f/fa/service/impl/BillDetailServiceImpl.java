package com.f.fa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f.fa.mapper.BillDetailMapper;
import com.f.fa.pojo.BillDetail;
import com.f.fa.service.BillDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BillDetailServiceImpl extends ServiceImpl<BillDetailMapper, BillDetail> implements BillDetailService {

    @Resource
    private BillDetailMapper billDetailMapper;


}
