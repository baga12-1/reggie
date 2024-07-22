package com.tianjie.controller;

import com.tianjie.service.OrderDetailService;
import com.tianjie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 地址簿管理
 */
@Slf4j
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

   @Autowired
    private OrderDetailService orderDetailService;
}
