package com.example.bootdemo.mapper;

import com.example.bootdemo.pojo.UserLogin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLoginMapper {
    // 查询
    public List<UserLogin> queryAll();

    // 添加
    public int add(UserLogin userLogin);

    // 根据用户名查询数据
    public UserLogin queryByName(String username);
}
