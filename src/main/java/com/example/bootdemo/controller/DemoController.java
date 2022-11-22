package com.example.bootdemo.controller;

import com.example.bootdemo.pojo.UserLogin;
import com.example.bootdemo.services.UserLoginServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class DemoController {
    @Autowired
    UserLoginServicesImpl userLoginServicesImpl;

    //查询全部
    @GetMapping("/list")
    public List<UserLogin> list() {
        return userLoginServicesImpl.queryAll();
    }
}
