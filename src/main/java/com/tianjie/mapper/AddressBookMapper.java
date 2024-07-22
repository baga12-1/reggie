package com.tianjie.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianjie.entity.AddressBook;
import com.tianjie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
