package com.f.fa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.f.fa.pojo.Bill;

import java.util.List;

public interface BillMapper extends BaseMapper<Bill> {

    List<String> labels();
}