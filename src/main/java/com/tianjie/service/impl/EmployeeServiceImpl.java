package com.tianjie.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjie.entity.Employee;
import com.tianjie.mapper.EmployeeMapper;
import com.tianjie.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
