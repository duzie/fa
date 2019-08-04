package com.f.fa;

import com.f.fa.pojo.Bill;
import com.f.fa.pojo.BillDetailVo;
import com.f.fa.pojo.enums.CycleType;
import com.f.fa.service.BillDetailService;
import com.f.fa.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FaApplicationTests {

    @Autowired
    BillService billService;

    @Autowired
    BillDetailService billDetailService;

    @Test
    public void add() {
        Bill bill = new Bill();
        bill.setName("京东金条");
        bill.setAmount(new BigDecimal(200));
        bill.setCycle(CycleType.MONTH.getValue());
        bill.setPeriods(9);
        bill.setStartDate(new Date());
        billService.add(bill);
    }

    @Test
    public void findBillDetail() {
        List<BillDetailVo> billDetails = billDetailService.findBillDetails();
        for (BillDetailVo billDetail : billDetails) {
            log.debug("{}", billDetail);
        }
    }

}
