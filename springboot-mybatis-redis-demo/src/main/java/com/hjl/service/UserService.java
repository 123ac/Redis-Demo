package com.hjl.service;

import com.hjl.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> select();

    User findById(String id);

    int delete(String id);

    int update (String id);

}
