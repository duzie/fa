package com.f.fa.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.f.fa.pojo.Bill;
import com.f.fa.pojo.BillMonthDetailVo;
import com.f.fa.service.BillDetailService;
import com.f.fa.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class BillController {

    @Autowired
    BillDetailService billDetailService;

    @Autowired
    BillService billService;

    @GetMapping
    public String index(Model model) {
        List<BillMonthDetailVo> billDetails = billDetailService.findBillDetails();
        model.addAttribute("billMonth", billDetails);
        return "index";
    }

    @GetMapping("add")
    public String addView(Model model) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_date");
        List list = billService.list(queryWrapper);
        model.addAttribute("list", list);
        List<String> labels = billService.labels();
        model.addAttribute("labels", labels);
        return "add";
    }

    @PostMapping("add")
    public String add(Bill bill) {
        billService.add(bill);
        return "redirect:./add";
    }

    @GetMapping("delete")
    public String delele(long id) {
        billService.deleteBill(id);
        return "redirect:./add";
    }
}
