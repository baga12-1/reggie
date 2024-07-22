package com.tianjie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianjie.common.R;
import com.tianjie.dto.DishDto;
import com.tianjie.dto.SetmealDto;
import com.tianjie.entity.*;
import com.tianjie.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SetMealService setMealService;

    @Autowired
    private SetMealDishService setMealDishService;

    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        setMealService.saveWithDish(setmealDto);
        return R.success("添加套餐成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<Setmeal> pageInfo = new Page<>(page,pageSize);
        Page<SetmealDto> setmealDtoPage = new Page<>();
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null,Setmeal::getName,name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setMealService.page(pageInfo,queryWrapper);

        BeanUtils.copyProperties(pageInfo,setmealDtoPage,"records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> setMealDtos = records.stream().map(item -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            Category category = categoryService.getById(item.getCategoryId());
            if (category != null) {
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).toList();

        setmealDtoPage.setRecords(setMealDtos);
        return R.success(setmealDtoPage);
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        setMealService.deleteWithDish(ids);
        return R.success("删除成功");
    }

    @PostMapping("/status/0")
    public R<String> stop(@RequestParam List<Long> ids){

        LambdaUpdateWrapper<Setmeal> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Setmeal::getStatus,0);
        updateWrapper.in(Setmeal::getId,ids);
        setMealService.update(updateWrapper);

        return R.success("状态更新为停售成功");
    }

    @PostMapping("/status/1")
    public R<String> start(@RequestParam List<Long> ids){

        LambdaUpdateWrapper<Setmeal> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Setmeal::getStatus,1);
        updateWrapper.in(Setmeal::getId,ids);
        setMealService.update(updateWrapper);

        return R.success("状态更新为起售成功");
    }

    @GetMapping("/{id}")
    public R<SetmealDto> getById(@PathVariable Long id){

        SetmealDto setmealDto = setMealService.getByIdOfDish(id);

        return R.success(setmealDto);
    }

    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto){

        setMealService.updateSetMeal(setmealDto);
        return R.success("套餐更新成功");
    }

    @GetMapping("/list")
    public R<List<SetmealDto>> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId() != null,Setmeal::getCategoryId,setmeal.getCategoryId());
        queryWrapper.eq(Setmeal::getStatus,1);
        queryWrapper.like(setmeal.getName() != null,Setmeal::getName,setmeal.getName());
//        queryWrapper.orderByAsc(Setmeal::getSort).orderByDesc(Setmeal::getUpdateTime);
        List<Setmeal> setmeals = setMealService.list(queryWrapper);
        List<SetmealDto> setmealDtoList = setmeals.stream().map(item ->{
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item,setmealDto);
            LambdaQueryWrapper<SetmealDish> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(SetmealDish::getSetmealId,item.getId());
            List<SetmealDish> setmealDishes = setMealDishService.list(queryWrapper1);
            setmealDto.setSetmealDishes(setmealDishes);
            return setmealDto;
        }).toList();
        return R.success(setmealDtoList);
    }
}
