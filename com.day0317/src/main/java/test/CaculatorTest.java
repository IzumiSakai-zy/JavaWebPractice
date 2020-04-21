package test;

import comzy.Caculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class CaculatorTest {
    @Before
    public void inite(){
        System.out.println("Before方法执行了");
    }
    @After
    public void close(){
        System.out.println("After方法被执行了");
    }

    @Test
    public void addTest(){
        //1、创建对象
        Caculator caculator=new Caculator();
        //2、调用方法
        int result=caculator.sub(9,4);
        //3、断言
        Assert.assertEquals(5,result);
    }

    @Test
    public void caculatorTest() throws Exception{
        Class caculatorClass=Class.forName("comzy.Caculator");
        Method method=caculatorClass.getMethod(String.valueOf(String.class),int.class);
        Constructor constructor=caculatorClass.getConstructor();
        constructor.newInstance();
    }
}
