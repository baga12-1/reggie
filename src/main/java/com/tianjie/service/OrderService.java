package com.tianjie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianjie.entity.AddressBook;
import com.tianjie.entity.Orders;

public interface OrderService extends IService<Orders> {

    void submit(Orders orders);
}
