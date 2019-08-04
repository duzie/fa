package com.f.fa.controller;

import com.f.fa.pojo.BillDetailVo;
import com.f.fa.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BillController {

    @Autowired
    BillDetailService billDetailService;

    @GetMapping
    public String index(Model model) {
        List<BillDetailVo> billDetails = billDetailService.findBillDetails();
        model.addAttribute("bill", billDetails);
        return "index";
    }
}
