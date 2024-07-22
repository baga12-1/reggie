package com.tianjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianjie.dto.SetmealDto;
import com.tianjie.entity.Category;
import com.tianjie.entity.Setmeal;

import java.util.List;

public interface SetMealService extends IService<Setmeal> {

    void saveWithDish(SetmealDto setmealDto);
    void deleteWithDish(List<Long> ids);

    SetmealDto getByIdOfDish(Long id);

    void updateSetMeal(SetmealDto setmealDto);
}
