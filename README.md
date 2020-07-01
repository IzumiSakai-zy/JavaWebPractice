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
  * `Person.class`通过类名.Class方式，一般用于参数传递
  * `peoson.getClass()`通过Object类的getClass()方法获取
*********************
### 注解

* 作用分类

  * 用于编写文档，如javadoc文档。`@Parm, @Return, @Author`这类注解会在javadoc命令下被抽取成api文档

  * 用于编译检查，如`@Overide`检查方法是否为重写方法

  * 用于编程，如其他很多注解
* java预定义注解
  * `@Overide`用于检查方法是否重写
  * `@Deprecated`用于标注这个属性或方法等已经过时，不推荐使用，但强行使用依然OK
  * `@SuperessWarning`用于压制警告，即使有警告也不弹出来被压制住
* 自定义注解
  * 注解实质——继承了java.lang.annotation.Annotation的一个接口
  * 定义语句`public interface 注解名 extends java.lang.annotation.Annotation{...}`
* 元注解——描述注解的注解
  * `@Target`标注注解能够作用的位置
  * `@Retention`注解能够保留的阶段
  * `@Documented`表明是否被抽取到API文档中
  * `@Inherited`表明注解能否被继承 
* 解析注解——实际上是利用反射的机制
  * 获取含有注解的类的Class对象(字节码)`Class personClass=Person.class;`
  * 获取Class对象上的所有注解对象`Annotation[] annotations=Caculator.class.getAnnotations();`
  * 获取注解对象的值
* 注意事项

  * 大多数时候都是使用注解而不是自定义

  * 注解是给编译器(检查编译)和解析程序(解析以实现功能)使用

  * 注解不是程序的一部分，而是标签
* 利用注解实现自定义测试框架核心——通过字节码获取所有方法，判断上面有没有注解，如果有就执行
***************************
### JDBC
* 本质——Sun公司定义了一套操作所有关系数据库的接口，数据库厂商根据接口分别写出的实现类的集合就是对应数据库的java驱动。然后通过多态的方式，声明时使用顶层接口，实际对象时使用实现类来实现调用

* 核心对象及其作用
  * DriverManager对象
    * 注册驱动
    * 获取Connection对象
    
  * Connection对象
    * 获取执行SQL语句的对象Statement和PreparedStatement
    * 进行事务管理，设置是否自动提交事务
    
  * Statement和PreparedStatement对象
  
    * 执行SQL语句
    
  * ResultSet对象
  
    * 对结果集进行遍历
  
* PreparedStatement对象防止注入危险

  ```java
  //定义Sql语句
  String sql="select * from user where name=? and password=?";
  //获取PreparedStatement对象
  PreparedStatement preparedStatement=connection.preparedStatement(sql);
  //设置参数值
  preparedStatement.setString(1,"IzumiSakai");
  preparedStatement.setString(2,"123456");
  //执行sql语句
  ResultSet resultSet=preparedStatement.executeQuery();
  ```
  
* 事务操作

  ```java
  try{
      con.setAutoCommit(false);
      ...
      转账程序
      ...
      con.commmit();
  }catch(Exception e){
      con.rollback();
  }
  ```
***************
### HTML

* 静态资源——所有用户看到都是一样的资源

  * css, javascript, html, 文, 音, 视, 图
  
* 动态资源——不同用户看到会不一样的资源

  * jsp, servlet, php, asp

  * 请求动态资源，服务器执行动态资源，但最后返回的还是静态资源
  
* 三大静态资源作用
  * html——展示
  * css——美化布局
  * javascript——控制元素，具有动态效果
  
* html一些基本标签
  
  * 头标签<head></head>  用于指定html的一些属性，引入外部资源等
  
  * 链接标签<link rel="stylesheet" href="CSS/CssExample.css">  位置于<head>标签内部，用于引用资源，rel="stylesheet"表示当前页面与href所指定文档的关系，即说明的是href连接的文档是一个新样式表
  
  * 顶部标签 <!DOCTYPE html> 位置于文档最顶部，表明这是html文档
  
  * 图片标签<img src="./image/石原里美01.png" alt="石原里美"> 
    * "./"表示当前目录下；"../" 表示上一级目录下
    * `alt="石原里美"` 表示在图片加载失败时
    
  * 列表标签 <ol></ol>有序列表   <ul></ul>无序列表
  
    ```html 
    <!--有序列表
    	其中type的属性有"A,a,1"等-->
    <ol type="A">
        <li>第一项</li>
        <li>第二项</li>
    </ol>
    
    <!--无序列表
    	其中type的属性有"square,circle"等,表示项目符号的形状-->
    <ul type="circle">
         <li>第一项</li>
         <li>第二项</li>
         <li>第三项</li>
    </ul>
    ```
    
  * 链接标签<a></a>
  
    ```html
    <!--新建一个页跳转-->
    <a href="https://www.baidu.com" target="_blank">百度一下</a>
    
    <!--本页跳转，此种方式为默认方式-->
    <a href="https://www.baidu.com" target="_self">百度一下</a>
    
    <!--把图片设置成链接-->
    <a href="https://www.baidu.com"><img src="./image/石原里美01.png" alt="石原里美"></a>
    ```
    
  * <span></span>不换行；<div></div>要换行
  
  * 语义化标签<header></header>和<footer></footer>等。作用和<div></div>一样，只是方便理解
  
  * 表格标签<table></table>
  
    * 表格里面还可以继续嵌套表格
  
    ```html
    <table border="1">
        <!--标题-->
        <caption>表格</caption>
        <tr>
            <!--表头-->
            <th>姓名</th>
            <th>性别</th>
            <th>成绩</th>
        </tr>
        <tr>
            <!--向下合并列-->
            <td rowspan="2">小明</td>
            <td>男</td>
            <td>60</td>
        </tr>
        <tr>
            <td>小红</td>
            <!--横向合并行-->
            <td colspan="2">女</td>
        </tr>
    </table>
    ```
    
  * 表单标签<form></form>
  
    ```HTML
    <!--action是提交处理的url地址-->
    <form action="#" method="post">
        <!--此处使用的是form内嵌套table-->
        <table>
            <tr>
                <!--for可以与id进行绑定-->
                <td class="td_left"><label for="usernumber" >用户名：</label></td>
                <td><input  type="text" name="usernumber" id="usernumber" placeholder="请输入账号"></td>
            </tr>
            <!--单元radio的使用-->
            <tr>
                <td class="td_left"><label>性别：</label></td>
                <td><input  type="radio" name="sex" value="male">男
                <input  type="radio" name="sex" value="female">女</td>
            </tr>
            <!--input框的属性还能是date-->
            <tr>
                <td class="td_left"><label for="birthday">出生日期：</label></td>
                <td><input  type="date" name="birthday" id="birthday"></td>
            </tr>
            <tr>
                <td class="td_left"><label>验证码：</label></td>
                <td><input  type="text" name="testNumber" id="testnumber"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="注册" id="bt_rg"></td>
            </tr>
        </table>
    </form>
    ```
    
  * 输入标签<input></input>
  
    ```html
    <!--单选框-->
    <input  type="radio" name="sex" value="female" checked="checked">
    
    <!--多选框-->
    <input type="checkbox" value="math" name="subject" checked="checked">
    <input type="checkbox" value="English" name="subject">
    
    <!--日期-->
    <input  type="date" name="birthday">
    
    <!--隐藏域-->
    <input  type="hidden" name="id">
    ```
    
  * 下拉列表
  
    ```HTML
    <select name="subject">
        <option value="math">数学</option>
        <option value="Chinese" selected="selected">语文</option>
        <option value="English">英语</option>
    </select>
    ```
* 其他

  * height属性的单位是像素，因为网页高度的最大值是无限的；width的单位是百分数，因为网页宽度的最大值是固定的

* 非
******************
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

  
