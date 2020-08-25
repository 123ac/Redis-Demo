package com.hjl.dao;

import com.hjl.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> select();

    User findById(String id);

    int delete(String id);

    int update(String id);
}
