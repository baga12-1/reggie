package com.tianjie.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianjie.entity.Category;
import com.tianjie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
