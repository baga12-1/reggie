package com.tianjie.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianjie.entity.Category;
import com.tianjie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
