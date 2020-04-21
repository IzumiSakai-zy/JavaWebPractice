package com.test;

import com.zy.JdbcUtils;
import com.zy.User;
import com.zy.UserDao;
import org.junit.Test;

public class TestClass {
    @Test
    public void testJdbcUtils(){
        System.out.println(JdbcUtils.getDataSource());
    }

    @Test
    public void testUserDao(){
        UserDao userDao=new UserDao();
        User user=userDao.getUser("zhangsan","123456");
        System.out.println(user);
    }
}
