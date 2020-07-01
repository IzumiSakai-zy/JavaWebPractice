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
  
  * 头标签`<head></head>`  用于指定html的一些属性，引入外部资源等
  
  * 资源链接标签`<link rel="stylesheet" href="CSS/CssExample.css">  `位置于`<head>`标签内部，用于引用资源，rel="stylesheet"表示当前页面与href所指定文档的关系，即说明的是href连接的文档是一个新样式表
  
  * 链接标签`<a href="javascript:void(0)">删除内容</a>`。链接标签有被点击和跳转到指定URL两个功能，href="javascript:void(0)"语句屏蔽了功能二，屏蔽后可以设置onclick方法，实现一些自定义功能
  
  * 顶部标签 `<!DOCTYPE html>` 位置于文档最顶部，表明这是html文档
  
  * 图片标签`<img src="./image/石原里美01.png" alt="石原里美"> `
    * "./"表示当前目录下；"../" 表示上一级目录下
    * `alt="石原里美"` 表示在图片加载失败时
    
  * 列表标签 `<ol></ol>`有序列表   `<ul></ul>`无序列表
  
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
    
  * 链接标签`<a></a>`
  
    ```html
    <!--新建一个页跳转-->
    <a href="https://www.baidu.com" target="_blank">百度一下</a>
    
    <!--本页跳转，此种方式为默认方式-->
    <a href="https://www.baidu.com" target="_self">百度一下</a>
    
    <!--把图片设置成链接-->
    <a href="https://www.baidu.com"><img src="./image/石原里美01.png" alt="石原里美"></a>
    ```
    
  * `<span></span>`不换行；`<div></div>`要换行
  
  * 语义化标签`<header></header>`和`<footer></footer>`等。作用和`<div></div>`一样，只是方便理解
  
  * 表格标签`<table></table>`
  
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
    
  * 表单标签`<form></form>`
  
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
    
  * 输入标签`<input></input>`
  
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

******************
### CSS
* 作用——页面美化和布局控制

* 三种引入方式

  * 内联方式`<div style="color:red;">hello</div>`

  * 内部样式——`<head></head>`内定义样式
  ```html
    <head>
        <style>
            div{color: red;align-content: center}
        </style>
    </head>
    <body>
      <div style="color: red;">hello</div>
    </body>
  ```
  * 外部样式——定义在文件中，通过选择器来区分
  
    ```HTML
    <head>
        <link href="./CSS/CssExample.css" rel="stylesheet">
    </head>
    <body>
        <div class="rg_directLog">hello</div>
    </body>
    ```
  
* 选择器语法

  * id选择器`#birthday{vertical-align:center;height: 30px;padding: 10px;}`核心是#
  * 类选择器`.birthday{vertical-align:center;height: 30px;padding: 10px;}`核心是.
  * 元素选择器`div{vertical-align:center;height: 30px;padding: 10px;}`核心是元素名如div
  * 所有选择器`*{vertical-align:center;height: 30px;padding: 10px;}`核心是*
  
* 复合属性`boder: 3px solid red`中间以空格区分而不是逗号

* css案例。在笔记第24页
**************************
### JavaScript
* 概念：运行在客户端浏览器中，客户端有js解析引擎，不用编译直接解释执行

* 功能：增强交互体验

* 引入方式
  * 内部js：定义在任意位置，但不同的位置会赢影响执行顺序。如`<script>alert("hello javascript")</script>`
  * 外部js：定义在外部js文件中。`<script src="./js/jquery.js" />`
  
* 基本数据类型(首字符都是小写)

  * number：包括整数、小数、NAN

  * string：包括字符、字符串。js中无字符概念，统称字符串

  * boolean; null; undefined
  
* 语法
  * js是弱类型语言，变量定义为`var name="Izumi Sakai"`
  * typeof()运算，用于查看是啥类型
  * "+"运算符只能加减数字，遇到不是数字就强转，最终非整数小数会被转成NAN
  * "=="运算符若类型不同会先进行类型转换，而"==="不转换类型。`"123"==123`为true，`"123"===123`为false
  * 其他类型向boolean转换。“0”和“NAN”和空字符串为false，其他的number和string形为true
  * 分号可以写可以不写，但建议写
  * 定义变量时var用了是局部变量，没用是全局变量
  * 输出内容 `document.write("content");`和`document.writeln()`
  
* function的写法(方法自始至终都是一个对象)

  * 写法一`function func(a,b){alert(a+b)};`
  * 写法二`var func2=new function(a,b){document.write(a+b)};`
  * 特性：方法实际调用时只与方法名字有关，而与参数列表无关。`function func(a,b){alert(a);alert(b);}`这个方法调用`func(1)`就能成功
  
* Array对象

  * 创建

    * 方式一`Array array=new Array(5);`创建数组的长度是5
    * 方式二`Array array=new Array(5,true,"hah");`创建的数组长度为3，里面的元素类型可变。注意是()而不是[]
    * 方式三`var array=[3,true,"hello"]`
  * 特点
    * 方式二用的是()而不是[]
    * 不会发生越界访问问题，最多越界的类型是undefined
  * 方法
    * pop()  最后一个元素出队
    * push("hello")  添加一个元素
  
* Date对象

  * 创建`var date=new Date();`

  * 方法 toLocalString()  显示的格式与操作系统有关
  
* Math
  * 和java一样都是静态方法，直接使用就行
  * 方法
    * rando() 返回[0,1)的一个小数
    * ceil(4.2)  向上取整
    * floor(3.2)  向下取整
    * round(3.4)  四舍五入
  
* 正则对象
  * 创建`var reg=new RegExp("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");`
  * 方法  test("izumisakai@aliyun.com")。返回一个boolean值
  * 注意事项
    * 正则表达式开始符号是"^"，结束符号是"$"
    * 因为转义字符，"\"要写成"\\"
  
* Global对象

  *  定义：全局对象，其方法不用对象.方法的方式，直接调用

  * 方法

    * 解码编码方法。因为HTTP协议不支持中文，如百度的搜索框内容被编码传输，需要在界面展示的内容被解码

      * encodeURIComponent()和decodeURIComponent()两个方法和下面的区别就是支持的中文更多

      ```javascript
      var encode=encodeURI("百度")
      var decode=decodeURI(encode);
      document.writeln(decode);
      ```
      
    * `parseInt("432432");`字符串转数字，一位一位转，知道不是数字为止。  
    
    * `isNAN()`判断是不是NAN
  
* DOM——Document Object Model

  * 功能：控制HTML文档的内容的内容
  * document对象方法
    * `var element=document.getElementById("username");`把每个html标签抽取成元素
    * `var element=document.createElementById("div");`创建一个div的标签对象。属性内容啥都是空，但是可以自行调用方法添加
  * Element对象的属性  "src", "innerHTML", "innerTEXT", "onclick"等等
  * Element对象的方法
    * `setAttribute("style","color:red;");`
    * `removeAttribute("style");`
  * Node对象
    *  特点：所有的DOM对象类都是Node的子类
    *  方法
      * `appendChild(Element element)`
      * `removeChild(Element element)`
      * `replaceChild(Element element)`
  
* 事件

  * 创建 `<button onclick="alert("hello")" />`或者`<button onclick="func("hello")" />`
  * 使用思路：通过DOM获取到这个对象，然后设置绑定事件
  
* 图片切换案例

  * 重点核心：script代码一定要放在最后`<body></body>`后面，不然会有加载顺序的问题

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <title>图片切换</title>
  </head>
  <body>
      <img src="./image/柯南007.png" id="picture">
  </body>
  <script>
      var is007=true;
      var picture=document.getElementById("picture");
      picture.onclick=function () {
          if(is007){
              picture.src="./image/柯南007.png";
              is007=false;
          }else {
              picture.src="./image/柯南008.png";
              is007=true;
          }
      }
  </script>
  </html>
  ```
  
* BOM——Browser Object Model

  * 浏览器对象
    * window对象
    * Navigator对象
    * History对象
    * ……
  
* window对象

  * 特点：不用对象.方法使用，直接方法名使用方法
  * 方法
    * `alert("警告"); `用于弹出警告
    * `var isSure=confirm("确认要关闭吗？");`弹出确认警告，返回一个boolean值
    * `var content=prompt("请输入内容");`弹出一个对话框，接收用户输入内容，返回一个字符串
    * `window.close();`关闭这个窗口，谁调用关谁
    * `open("https://www.baidu.com");`弹出并返回一个新的window对象，但可能弹页会被浏览器拦截
    * `var id=setTiemout("alert('警告')",3000)`作用是3秒后弹一次警告，只弹一次。注意执行的内容必须有双引号，双引后内还要用引号就用单引号
    * `clearTimeout(id)`传入id清楚setTimeout()
    * `var id=setInterval("alert('警告')",3000)`作用是每隔3秒弹一次警告，弹无数次。注意执行的内容必须有双引号，双引后内还要用引号就用单引号
    * `clearInterval(id)`传入id清楚setInterval()
  
* 轮播图

  ```HTML
  </head>
  <body>
      <img src="./image/柯南007.png" id="loopImage">
  </body>
  <script>
      var loopImageElement=document.getElementById("loopImage");
      function loopImage(i) {
          var value;
          switch (i) {
              case 0:value="./image/柯南007.png";break;
              case 1:value="./image/柯南008.png";break;
              case 2:value="./image/柯南009.png";break;
              default:value="./image/柯南010.png";break;
          }
          return value;
      }
      var i=0;
      setInterval("loopImageElement.src=loopImage((i++)%4)",1000);
  </script>
  </html>
  ```
  
* 倒计时跳转

  ```HTML
  </head>
  <body>
      <span id="second">10</span>后跳转到百度
  </body>
  <script>
      var i=10;
      element=document.getElementById("second");
      function func(){
          element.innerText=i--;
          if (i==1)
              location.href="https://www.baidu.com";
      }
      setInterval("func()",1000);
  </script>
  </html>
  ```
  
* Location地址栏对象

  * 创建：直接location就行
  * 方法：location.reload()相当于F5刷新
  * 属性
    * var href=location.href 获取当前的URL
    * location.href="https://www.baidu.com" 强制跳转到百度
  
* 添加删除表格案例

  ```HTML
  <body>
      <input type="text" placeholder="请输入姓名" id="name">
      <input type="text" placeholder="请输入性别" id="gender">
      <input type="text" placeholder="请输入年龄" id="age">
      <a href="javascript:void(0);" id="add">添加</a>
      <table id="tb">
          <tr>
              <th>姓名</th>
              <th>性别</th>
              <th>年龄</th>
              <th>操作</th>
          </tr>
      </table>
  </body>
  <script>
      document.getElementById("add").onclick=function () {
          var name=document.getElementById("name").value;
          var gender=document.getElementById("gender").value;
          var age=document.getElementById("age").value;
  
          var tb=document.getElementById("tb");
          var tr=document.createElement("tr");
          tb.appendChild(tr);
  
          var tb1=document.createElement("td");
          tb1.innerHTML=name;
          var tb2=document.createElement("td");
          tb2.innerHTML=gender;
          var tb3=document.createElement("td");
          tb3.innerHTML=age;
          var tb4=document.createElement("td");
          var del=document.createElement("input");
          tb4.appendChild(del);
          del.setAttribute("type","button");
          del.setAttribute("value","删除");
          del.onclick=function(){
              var parent=del.parentNode.parentNode.parentNode;
              parent.removeChild(del.parentNode.parentNode);
          }
  
          tr.appendChild(tb1);
          tr.appendChild(tb2);
          tr.appendChild(tb3);
          tr.appendChild(tb4);
      }
  </script>
  ```
*******************
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

  
