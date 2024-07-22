package com.tianjie.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjie.entity.AddressBook;
import com.tianjie.entity.ShoppingCart;
import com.tianjie.mapper.AddressBookMapper;
import com.tianjie.mapper.ShoppingCartMapper;
import com.tianjie.service.AddressBookService;
import com.tianjie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {


}
