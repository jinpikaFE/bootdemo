package com.example.bootdemo;

import com.example.bootdemo.mapper.UserLoginMapper;
import com.example.bootdemo.pojo.UserLogin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class BootdemoApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    UserLoginMapper userLoginMapper;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        connection.close();
    }

    @Test
    public void toTest() {
        List<UserLogin> userLogins = userLoginMapper.queryAll();
        userLogins.forEach(e -> System.out.println(e));
    }

}
