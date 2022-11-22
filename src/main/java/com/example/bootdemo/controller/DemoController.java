package com.example.bootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bootdemo.annotation.AuthToken;
import com.example.bootdemo.model.ResponseTemplate;
import com.example.bootdemo.pojo.UserLogin;
import com.example.bootdemo.services.UserLoginServicesImpl;
import com.example.bootdemo.utils.ConstantKit;
import com.example.bootdemo.utils.Md5TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
@RequestMapping(value = "/userInfo", produces = "application/json;charset=UTF-8")
public class DemoController {
    Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    Md5TokenGenerator tokenGenerator;

    @Autowired
    UserLoginServicesImpl userLoginServicesImpl;

    /**
     * 查询全部
     */
    @GetMapping("/list")
    @AuthToken
    public List<UserLogin> list() {
        return userLoginServicesImpl.queryAll();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseTemplate login(@RequestBody UserLogin userLogin) {
        logger.info("username:" + userLogin.getUsername() + "      password:" + userLogin.getPassword());
        UserLogin userLogin1 = userLoginServicesImpl.getUser(userLogin);
        JSONObject result = new JSONObject();
        if (userLogin1 != null) {
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            String token = tokenGenerator.generate(userLogin.getUsername(), userLogin.getPassword());
            jedis.set(userLogin.getUsername(), token);
            //设置key生存时间，当key过期时，它会被自动删除，时间是秒
            jedis.expire(userLogin.getUsername(), ConstantKit.TOKEN_EXPIRE_TIME);
            jedis.set(token, userLogin.getUsername());
            jedis.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
            Long currentTime = System.currentTimeMillis();
            jedis.set(token + userLogin.getUsername(), currentTime.toString());

            //用完关闭
            jedis.close();

            result.put("status", "登录成功12");
            result.put("token", token);
        } else {
            result.put("status", "登录失败1");
        }
        return ResponseTemplate.builder()
                .code(200)
                .message("success")
                .data(result)
                .build();
    }
}
