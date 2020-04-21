<%--
  Created by IntelliJ IDEA.
  User: 泉水姐姐超级粉丝
  Date: 2020/4/20
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>longin</title>
    <script>
        window.onload=function () {
            document.getElementById("checkcodeId").onclick=function () {
                this.src="/com_day0420_checkCode_war_exploded/checkCodeServlet?time="+new Date().getTime();
            }
        }
    </script>
</head>
<body>
    <form action="/com_day0420_checkCode_war_exploded/loginServlet" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td><input type="text" name="checkcode"></td>
            </tr>
            <tr>
                <td colspan="2"><img id="checkcodeId" src="/com_day0420_checkCode_war_exploded/
                checkCodeServlet"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>
    <div><%= request.getAttribute("checkcodeError")==null? "":request.getAttribute("checkcodeError")%></div>
    <div><%= request.getAttribute("passwordError")==null? "":request.getAttribute("passwordError")%></div>
</body>
</html>
