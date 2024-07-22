package com.tianjie.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjie.common.CustomException;
import com.tianjie.entity.Category;
import com.tianjie.entity.Dish;
import com.tianjie.entity.Employee;
import com.tianjie.entity.Setmeal;
import com.tianjie.mapper.CategoryMapper;
import com.tianjie.mapper.EmployeeMapper;
import com.tianjie.service.CategoryService;
import com.tianjie.service.DishService;
import com.tianjie.service.EmployeeService;
import com.tianjie.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetMealService setMealService;

    @Override
    public void remove(Long id) {
        //判断分类是否关联有菜品
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        if(count1 > 0){
            throw new CustomException("关联有菜品，不可以删除该分类");
        }
        //判断分类是否关联有套餐
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setMealService.count(setmealLambdaQueryWrapper);
        if(count2 > 0){
            throw new CustomException("关联有套餐，不可以删除该分类");
        }
        super.removeById(id);
    }
}
