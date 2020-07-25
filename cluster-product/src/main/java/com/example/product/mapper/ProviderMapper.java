package com.example.product.mapper;

import com.example.common.entity.Provider;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProviderMapper {

    //useGeneratedKeys是否使用自增主键，keyProperty指定实体类中的哪一个属性封装主键值
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into provider(p_name) values(#{name})")
    int add(Provider provider);

    @Delete("delete from provider where p_id=#{id}")
    int delete(Integer id);

    @Update("update provider set p_name=#{name} where p_id=#{id}")
    int update(Provider provider);

    @Select("select p_id id, p_name name from provider where p_id=#{id}")
    Provider get(Integer id);
}
