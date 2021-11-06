package com.f.fa.controller;

import com.f.fa.pojo.Goods;
import com.f.fa.pojo.TianjiOrder;
import com.f.fa.service.TianjiOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tianji")
public class TianjiController {

    @Autowired
    private TianjiOrderService tianjiOrderService;

    @PostMapping("/price")
    public List<Goods> price(@RequestBody List<TianjiOrder> tianjiOrderList) {
        tianjiOrderService.saveOrUpdateBatch(tianjiOrderList);
        List<Goods> list = new ArrayList<>();
        {
            Goods goods = new Goods("全国通用联通10元", 0.4);
            list.add(goods);
        }
        {
            Goods goods = new Goods("全国通用移动20元", 0.4);
            list.add(goods);
        }
        return list;
    }
}
