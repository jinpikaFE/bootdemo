package com.example.bootdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data 就是lombok 的注解 自动生成了set get
@Data
@AllArgsConstructor
@NoArgsConstructor
//@TableName(value = "user_info")  //@TableName 对应你的数据库表名
public class UserLogin {
    public String username;
    public String password;
    //    @TableId(value = "id",type = IdType.AUTO)  //@TableId 说明这条数据自增长也是对应数据库自增长的
    private Integer id;

    public String getUsername() {
        return username;
    }
}
