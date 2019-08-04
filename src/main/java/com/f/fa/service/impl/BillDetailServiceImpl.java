package com.f.fa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f.fa.mapper.BillDetailMapper;
import com.f.fa.pojo.BillDetail;
import com.f.fa.pojo.BillDetailVo;
import com.f.fa.service.BillDetailService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BillDetailServiceImpl extends ServiceImpl<BillDetailMapper, BillDetail> implements BillDetailService {

    @Resource
    private BillDetailMapper billDetailMapper;

    @Override
    public List<BillDetailVo> findBillDetails() {
        QueryWrapper<BillDetail> wrapper = new QueryWrapper<>();
        wrapper.ge("bill_date", DateUtils.truncate(new Date(), Calendar.DATE));
        List<BillDetail> list = list(wrapper);
        Map<Date, List<BillDetail>> collect = list.stream().collect(Collectors.groupingBy(BillDetail::getBillDate));
        Set<Date> dates = collect.keySet();

        List<BillDetailVo> billDetailVos = new ArrayList<>();
        for (Date date : dates) {
            BillDetailVo billDetailVo = new BillDetailVo();
            billDetailVo.setBillDate(date);
            billDetailVo.setAmount(collect.get(date).stream().mapToDouble(b->b.getAmount().doubleValue()).sum());
            billDetailVo.setBillDetails(collect.get(date));
            billDetailVos.add(billDetailVo);
        }
        billDetailVos.sort(Comparator.comparing(BillDetailVo::getBillDate));
        return billDetailVos;
    }
}
