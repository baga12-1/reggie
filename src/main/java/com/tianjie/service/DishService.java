package com.tianjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianjie.dto.DishDto;
import com.tianjie.entity.Category;
import com.tianjie.entity.Dish;

public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);
}
