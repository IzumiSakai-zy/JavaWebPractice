# JavaWebPractice
It shows the process of my java web learning
## 通过github上传来作为打卡吧，一天天的慢慢学
*************************************
**************************************
### com.day0426-CaseTest —— CS架构连接服务器实现CRUD和一些其他显示
   * 环境
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
       * DeleteServlet.class
          * 调用**业务逻辑层**和**数据库访问层**删除一个用户
          * 重定向跳转到**UserListServlet**(因为要更新查询，不然内容不变)
       * 遇到的问题
          * 因为数据库中的index值是int类型，而获取的参数是string类型，必须要进行转化。
          * 写SQL语句时**delete from user where index = ?**是错误的；必须写成**delete from user where `index` = ?**，index要有**单引号**。在这卡了很久很久
   
      
