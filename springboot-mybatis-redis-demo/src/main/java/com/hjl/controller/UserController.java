package com.hjl.controller;

import com.hjl.pojo.User;
import com.hjl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = {"/select"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public List<User> getAllUsers() {
        List<User> list = userService.select();
        return list;
    }

    @GetMapping("/findById/{id}")
    public User test(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    @GetMapping("/del/{id}")
    public int delele(@PathVariable("id") String id) {
        return userService.delete(id);
    }

    @GetMapping("/up/{id}")
    public int up(@PathVariable("id") String id) {
        return userService.update(id);

    }
}
