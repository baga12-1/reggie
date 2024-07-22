package com.tianjie.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjie.common.CustomException;
import com.tianjie.entity.AddressBook;
import com.tianjie.entity.Category;
import com.tianjie.entity.Dish;
import com.tianjie.entity.Setmeal;
import com.tianjie.mapper.AddressBookMapper;
import com.tianjie.mapper.CategoryMapper;
import com.tianjie.service.AddressBookService;
import com.tianjie.service.CategoryService;
import com.tianjie.service.DishService;
import com.tianjie.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {


}
