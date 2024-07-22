package com.tianjie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjie.entity.User;
import com.tianjie.mapper.UserMapper;
import com.tianjie.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
