package com.f.fa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f.fa.mapper.TianjiOrderMapper;
import com.f.fa.pojo.TianjiOrder;
import com.f.fa.service.TianjiOrderService;
import org.springframework.stereotype.Service;

@Service
public class TianjiOrderServiceImpl extends ServiceImpl<TianjiOrderMapper, TianjiOrder> implements TianjiOrderService {
}
