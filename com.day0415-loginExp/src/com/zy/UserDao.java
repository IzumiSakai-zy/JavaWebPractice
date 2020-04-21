package com.zy;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    public static JdbcTemplate jt=new JdbcTemplate(JdbcUtils.getDataSource());
    public User getUser(String username,String password){
        User user = jt.queryForObject("select * from users where username=? and password=?",
                new BeanPropertyRowMapper<>(User.class), username, password);
        return user;
    }
}
