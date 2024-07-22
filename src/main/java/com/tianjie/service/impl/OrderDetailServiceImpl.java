package com.tianjie.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjie.entity.AddressBook;
import com.tianjie.entity.OrderDetail;
import com.tianjie.mapper.AddressBookMapper;
import com.tianjie.mapper.OrderDetailMapper;
import com.tianjie.service.AddressBookService;
import com.tianjie.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {


}
