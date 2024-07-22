package com.tianjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianjie.entity.Category;
import com.tianjie.entity.Employee;

public interface CategoryService extends IService<Category> {
    //根据id删除分类
    void remove(Long id);
}
