package com.f.fa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.f.fa.pojo.Bill;

public interface BillService extends IService<Bill> {

     void add(Bill bill);

     void deleteBill(long id);
}
