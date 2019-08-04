package com.f.fa.pojo;

import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Data
public class BillDetailVo {


    private Date billDate;

    private Double amount;

    private List<BillDetail> billDetails;

    public Date getBillByMonth(){
        return DateUtils.truncate(this.billDate, Calendar.MONTH);
    }

}
