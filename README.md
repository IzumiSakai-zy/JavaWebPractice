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
   * 读取展示数据库的数据
       * index.jsp
          * 首先导入css,js依赖
          * 接着点击根据完成路径跳转到**userListServlet**
       * UserListServlet.class
          * 是一个**HttpServlet**
          * 调用**业务逻辑层**和**数据库访问层**获得数据
          * 使用**转发**方式跳转到list.jsp
          * 通过jstl的foreach循环将request域中的数据展示输出
