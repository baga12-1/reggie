package com.tianjie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianjie.common.R;
import com.tianjie.dto.DishDto;
import com.tianjie.entity.Category;
import com.tianjie.entity.Dish;
import com.tianjie.entity.DishFlavor;
import com.tianjie.service.CategoryService;
import com.tianjie.service.DishFlavorService;
import com.tianjie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
  菜品管理
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){

        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<Dish> pageInfo = new Page<>(page,pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null,Dish::getName,name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo,queryWrapper);

        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");

        List<Dish> dishes = pageInfo.getRecords();
        List<DishDto> dishDtos = dishes.stream().map(item -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Category category = categoryService.getById(item.getCategoryId());
            if(category != null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).toList();

        dishDtoPage.setRecords(dishDtos);
        return R.success(dishDtoPage);
    }

    @GetMapping("{id}")
    public R<DishDto> getDishInfoById(@PathVariable Long id){

        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }


    @PutMapping
    public R<String> updateWithFlavor(@RequestBody DishDto dishDto){

        dishService.updateWithFlavor(dishDto);
        return R.success("修改菜品成功");
    }


    @DeleteMapping
    @Transactional
    public R<String> delete(Long[] ids){
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dish::getId,ids);
        dishService.remove(queryWrapper);

        LambdaQueryWrapper<DishFlavor> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(DishFlavor::getDishId,ids);
        dishFlavorService.remove(queryWrapper2);
        return R.success("删除成功");
    }

    @PostMapping("/status/0")
    @Transactional
    public R<String> stop(Long[] ids){
//        System.out.println(Arrays.toString(ids));
        LambdaUpdateWrapper<Dish> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Dish::getStatus,0).in(Dish::getId,ids);
        dishService.update(updateWrapper);
        return R.success("菜品状态更新成功");
    }

    @PostMapping("/status/1")
    @Transactional
    public R<String> start(Long[] ids){

        LambdaUpdateWrapper<Dish> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Dish::getStatus,1).in(Dish::getId,ids);
        dishService.update(updateWrapper);
        return R.success("菜品状态更新成功");
    }

    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){

            LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(dish.getCategoryId() != null,Dish::getCategoryId,dish.getCategoryId());
            queryWrapper.eq(Dish::getStatus,1);
            queryWrapper.like(dish.getName() != null,Dish::getName,dish.getName());
            queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
            List<Dish> dishes = dishService.list(queryWrapper);
            List<DishDto> dishDtoList = dishes.stream().map(item ->{
                DishDto dishDto = new DishDto();
                BeanUtils.copyProperties(item,dishDto);
                LambdaQueryWrapper<DishFlavor> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(DishFlavor::getDishId,item.getId());
                List<DishFlavor> dishFlavors = dishFlavorService.list(queryWrapper1);
                dishDto.setFlavors(dishFlavors);
                return dishDto;
            }).toList();
            return R.success(dishDtoList);
    }
}
