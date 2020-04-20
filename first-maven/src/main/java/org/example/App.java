package org.example;

import com.day0301.Account;
import com.google.gson.internal.$Gson$Preconditions;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //1、获取核心容器对象  ApplicationContext继承了顶层接口BeanFactory
        ApplicationContext ac=new ClassPathXmlApplicationContext(
                "bean.xml");//加载类路径下的配置文件
        ApplicationContext ac2=new FileSystemXmlApplicationContext(
                "D:\\Program\\first-maven\\src\\main\\resources\\bean.xml");//加载磁盘路径下的配置文件
        //2、根据ID获取对象
        Account account=(Account) ac.getBean("Account");//强转类型
        Account secondAccount=ac.getBean("Account",Account.class);//类.class已转类型
        //3、使用创建出来的对象
        account.printHello();
        System.out.println("Hello WOrld");
    }
}