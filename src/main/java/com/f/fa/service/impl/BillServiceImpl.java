package com.f.fa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f.fa.mapper.BillMapper;
import com.f.fa.pojo.Bill;
import com.f.fa.pojo.BillDetail;
import com.f.fa.pojo.enums.CycleType;
import com.f.fa.service.BillDetailService;
import com.f.fa.service.BillService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {

    @Resource
    private BillMapper billMapper;

    @Autowired
    private BillDetailService billDetailService;

    @Override
    public void add(Bill bill) {
        bill.setCreateDate(new Date());
        this.save(bill);
        int cycle = bill.getCycle();
        CycleType cycleType = CycleType.getCycleType(bill.getCycle());
        if (cycleType == null) {
            throw new RuntimeException("周期不能为空");
        }
        Date startDate = bill.getStartDate();
        List<BillDetail> billDetailList = new ArrayList<>();
        for (int i = 0;i< bill.getPeriods();i++  ){
            BillDetail billDetail = new BillDetail();
            billDetail.setAmount(bill.getAmount());
            billDetail.setBillId(bill.getId());
            billDetail.setBillName(bill.getName());
            billDetail.setBillDate(DateUtils.truncate(startDate, Calendar.DATE));
            billDetailList.add(billDetail);

            switch (cycleType) {
                case WEEK:
                    startDate = DateUtils.addWeeks(startDate, 1);
                    break;
                case MONTH:
                    startDate = DateUtils.addMonths(startDate, 1);
                    break;
                case YEAR:
                    startDate = DateUtils.addYears(startDate, 1);
                    break;

            }
        }
        billDetailService.saveBatch(billDetailList);
    }

    @Transactional
    @Override
    public void deleteBill(long id) {
        removeById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("bill_id", id);
        billDetailService.removeByMap(map);
    }
}
