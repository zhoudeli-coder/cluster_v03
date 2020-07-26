package com.example.product.service.mapper;

import com.example.common.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommodityMapper {
    int insert(Commodity commodity);

    int delete(Integer id);

    int update(Commodity commodity);

    Commodity selectOne(Integer id);
}
