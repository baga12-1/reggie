package com.tianjie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.tianjie.common.BaseContext;
import com.tianjie.common.R;
import com.tianjie.entity.AddressBook;
import com.tianjie.entity.Orders;
import com.tianjie.service.AddressBookService;
import com.tianjie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址簿管理
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

   @Autowired
    private OrderService orderService;

   @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
       orderService.submit(orders);
       return R.success("订单提交成功");
   }

}
