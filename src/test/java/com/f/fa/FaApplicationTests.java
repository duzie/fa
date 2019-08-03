package com.f.fa;

import com.f.fa.pojo.Bill;
import com.f.fa.pojo.enums.CycleType;
import com.f.fa.service.BillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaApplicationTests {

    @Autowired
    BillService billService;

    @Test
    public void contextLoads() {
        Bill bill = new Bill();
        bill.setName("网商贷");
        bill.setAmount(new BigDecimal(300));
        bill.setCycle(CycleType.MONTH.getValue());
        bill.setPeriods(6);
        bill.setStartDate(new Date());
        billService.add(bill);
    }

}
