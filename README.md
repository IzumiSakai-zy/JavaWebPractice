# JavaWebPractice
It shows the process of my java web learning
*************************************
### Junit单元测试
* 测试分类
  * 黑盒测试——不用写代码
  * 白盒测试——需要写代码
  
* 测试步骤

  * 导入Junit依赖

  * 创建一个类，类名为 "测试类Test"

  * 定义一个可以独立运行的方法。要求访问类型为public，返回值为void，参数列表为空

  * 在这个方法上面加上`@Test`注解
```java
public class  CaculatorTest {
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
}
```
* 其他
  * `@Before`一般用于资源申请, `@After`一般用于资源释放
  * `Assert.assertEquals(5,result);`这是断言的写法
************************
### 反射

* 定义：框架设计的灵魂所在，将类的各个部分封装成对象
* 一个类的三个阶段
  * 源码阶段
    * Peroson.java源文件被javac编译器编译成Peroson.class文件
    * 这个Person.class文件别划分为三个部分——成员属性，构造函数，成员方法
  * Class类的对象阶段（Class是一个类，类名的Class）
    * Person.class文件在类加载器classLoader的加载下变成一个对象
    * 这个对象有方法`public Field[] getFields()`, `public Method[] getMethods()`, `public Constructor<?>[] getConstructors()`可以获得成员属性，构造函数，成员方法
    * 还有获得注解，包名，类名等的对应方法，总之能获得与这个类相关的一切信息
  * 运行时阶段
    * 此时已经实例化成了一个真正对象，一般通过 "new Person()"方式
* 反射与框架的一些联系
  * 创建对象的时候为了尽量降低耦合都在编译阶段把类停留在Class类对象阶段
  * 需要使用时才通过Class类对象的方法创建出实例化对象
* 获取Class对象的三种方式
  * `Class.forName(com.service.AccountService)`通过Class类的静态方法forName()方法
  * `Person.Class`通过类名.Class方式，一般用于参数传递
  * `peoson.getClass()`通过Object类的getClass()方法获取
### 实现简单CRUD
   *  环境
       * JDK1.8.0_241
       * TomCat 8.5.54
   * IDE
       * Idea
   * 依赖jar包
       * Bootstrap
       * Jquery-2.1.0.min.js
       * jstl
       * mysql-connector-java-8.0.15.jar（连接mysql）
       * spring template(包含多个sping依赖，用于封装jdbc)
       * spring bean(包含多个sping依赖，用于数据库对象)
       * commons-beanutils-1.8.3.jar（用于通过map字典参数来封装成bean对象）
   * 读取展示数据库的数据
       * index.jsp
          * 首先导入css,js依赖
          * 接着点击根据完成路径跳转到**userListServlet**
       * UserListServlet.class
          * 是一个**HttpServlet**
          * 调用**业务逻辑层**和**数据库访问层**获得数据
          * 使用**转发**方式跳转到list.jsp
       * list.jsp
          * 通过jstl的foreach循环将request域中的数据展示输出
       * 遇到的问题
          * 开始启动时要**打开mysql**并执行**set global time_zone = '+8:00'**指令
          * 返回button不能直接在其里面写**href**属性。可以**<a>标签**里面套一个button，href属性写<a>标签中
   * 添加用户
       * list.jsp
          * 点击list.jsp页面上的添加联系人按钮就跳转到**addServlet**
          * 通过表单通知服务器需要添加的用户详细信息
       * AddServlet.class
          * 调用**业务逻辑层**和**数据库访问层**添加一个用户
          * 重定向跳转到**UserListServlet**(因为要更新查询，不然内容不变)
       * 遇到的问题
          * list.jsp中表单的**name**属性值必须与**User**类中定义的属性名字一模一样，不然转成bean对象会出错
          * 静态页面间的相互跳转最好使用**绝对路径**
          * 涉及到客户端向服务器发送数据时，servlet代码的第一句一定是**设置编码**
          * 重定向是**resp**的方法，且最好加上**req.getContextPath()**
          * 添加客户DAO层要使用**update（）**，不能用**excute（）**
   * 删除用户
        * list.jsp
          * 点击list.jsp页面上的删除联系人按钮就跳转到**deleteServlet**
          * 通过**？index=${user.index}*通知服务器需要删除的用户的id
          * 为了增强用户体验。还必须增加是否确认删除的判断，代码如下  
            ``href="javascript:deleteUser(${user.index});"``  
            ``function deleteUser(index) {
                if (confirm("你确定要删除吗？"))
                location.href="${pageContext.request.contextPath}/deleteServlet?index="+index;}``
       * DeleteServlet.class
          * 调用**业务逻辑层**和**数据库访问层**删除一个用户
          * 重定向跳转到**UserListServlet**(因为要更新查询，不然内容不变)
       * 遇到的问题
          * 因为数据库中的index值是int类型，而获取的参数是string类型，必须要进行转化。
          * 写SQL语句时**delete from user where index = ?**是错误的；必须写成**delete from user where `index` = ?**，index要有**数字1左边的符号**。在这卡了很久很久。
        * 修改用户
        * list.jsp
          * 点击list.jsp页面上的修改联系人按钮就跳转到**writeBackServlet**
          * 通过**？index=${user.index}*通知服务器需要修改的用户的id
       * WriteBackServlet.class
          * 调用**业务逻辑层**和**数据库访问层**返回一个根据id值而查询到的**用户**
          * **转发**到**update.jsp**(用于回写内容)
       * update.jsp
          * 这个页面没有index属性值，需要使用input按钮**hidden属性**来保存从writeBackServlet获得的index值
          * 通过**writeBackServlet**转发的User对象对表单进行回写
          * 用户按照修改意愿对表单进行修改
          * 修改结束后点提交到**updateServlet**进行处理
       * UpdateServlet.class
          * 将获取的数据封装成一个User对象
          * 调用**业务逻辑层**和**数据库访问层**将user作为参数进行更新处理
              * 数据库访问层主要通过**index值**锁定对象。然后把其他所有属性值重写一遍。
       * 遇到的问题
          * **queryForObject()**方法以及其他有关数据库操作的方法。**？**占用最后赋值的参数永远都在参数的最后
          * 性别**单选框**的回写有难度，使用如下代码：  
            ``<c:if test="${user.gender=='男'}"> ``<br>
              ``<input type="radio" name="gender" value="男" checked="checked"/>男``<br>
              ``<input type="radio" name="gender" value="女"/>女``<br>
            ``</c:if>``  
            其中`` test="${user.gender=='男'}" ``写法要特别注意**单引号**和**布尔值全写在ef表达式内**
          * **input**按钮可以加上**readonly属性**
          * update的SQL语句顺序是** update set where **，where排在最后

​      

```

```