package com.hjl.service.impl;

import com.hjl.dao.UserMapper;
import com.hjl.pojo.User;
import com.hjl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service(value="UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<User> select() {
        return userMapper.select();
    }

    @Override
    public User findById(String id) {
        String key = "user_"+id;
        //先从缓存获取用户，如果没有再从数据库获取
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        boolean haskey = redisTemplate.hasKey(key);
        if(haskey){
            User user = valueOperations.get(key);
            System.out.println("缓存中获取数据=="+user.getName());
            return user;
        }else {
            User user = userMapper.findById(id);
            System.out.println("从数据库=="+user.getName());
            valueOperations.set(key,user,5, TimeUnit.HOURS);  //写入缓存
            return user;
        }
    }

    @Override
    public int delete(String id) {
        int result = userMapper.delete(id);
        String key = "user_"+id;

        //先删除数据库数据再删除缓存数据
        if(result != 0){
            if(redisTemplate.hasKey(key)){
                redisTemplate.delete(key);
            }
        }
        return  result;
    }

    @Override
    public int update(String id) {
        int result = userMapper.update(id);
        String key = "user_" + id;
        if(result!=0){
            if(redisTemplate.hasKey(key)){
                redisTemplate.delete(key);
            }
            //重新写入缓存
            User user = userMapper.findById(id);
            redisTemplate.opsForValue().set(key,user,5,TimeUnit.HOURS);
        }
        return result;
    }

}
