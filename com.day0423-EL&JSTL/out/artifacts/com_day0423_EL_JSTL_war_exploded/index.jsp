<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <c:if test="${number==5}">从request域获得的数字是5</c:if>
  <hr>
  <c:forEach items="${strList} " var="str">
    ${str}<hr>
  </c:forEach>
  </body>
</html>
