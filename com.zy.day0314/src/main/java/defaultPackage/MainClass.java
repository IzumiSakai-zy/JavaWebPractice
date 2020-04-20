package defaultPackage;

import comDay0314.TestClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
    public static void main(String[] args){
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        TestClass testClass=ac.getBean("testClass", TestClass.class);
        testClass.sayHello();
        System.out.println(testClass.getStr());
    }
}
/*
* <?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
    <context:annotation-config/>
</beans>
* */
/*
*<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--把对象的创建交给spring来管理-->
    <bean id="TextClass" class="comDay0314.TestClass" scope="prototype"></bean>
</beans>
* */
