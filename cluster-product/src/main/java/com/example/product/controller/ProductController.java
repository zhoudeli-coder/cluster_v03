package com.example.product.controller;

import com.example.common.entity.Provider;
import com.example.product.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product/providers")
public class ProductController {

    @Autowired
    private ProviderMapper providerMapper;
    
    @PostMapping
    public String add(Provider provider){
        providerMapper.insert(provider);
        return "添加成功" + provider.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        providerMapper.delete(id);
        return "删除成功";
    }

    @PutMapping
    public String update(Provider provider) {
        providerMapper.update(provider);
        return "修改成功";
    }

    @GetMapping("/{id}")
    public Provider get(@PathVariable("id") Integer id) {
        return providerMapper.selectOne(id);
    }
}
