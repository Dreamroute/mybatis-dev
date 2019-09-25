package com.github.dreamroute.mybatis.fly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.dreamroute.mybatis.fly.domain.User;

public interface UserMapper {
    
    int insertUser(User user);
    
    List<User> selectById(@Param("id") Long id);

}
