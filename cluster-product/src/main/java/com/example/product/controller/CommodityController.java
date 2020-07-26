package com.example.product.controller;

import com.example.common.entity.Commodity;
import com.example.common.entity.Provider;
import com.example.product.service.mapper.CommodityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product/commodities")
public class CommodityController {

    @Autowired
    private CommodityMapper commodityMapper;
    
    @PostMapping
    public String add(Commodity commodity){
        commodityMapper.insert(commodity);
        return "添加成功" + commodity.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        commodityMapper.delete(id);
        return "删除成功";
    }

    @PutMapping
    public String update(Commodity commodity) {
        commodityMapper.update(commodity);
        return "修改成功";
    }

    @GetMapping("/{id}")
    public Commodity get(@PathVariable("id") Integer id) {
        return commodityMapper.selectOne(id);
    }
}
