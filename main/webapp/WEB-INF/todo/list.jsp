<%--
  Created by IntelliJ IDEA.
  User: choij
  Date: 2024-03-07
  Time: 오전 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--taglib은 약속된 양식이다. 출력하는  --%>
<html>
<head>
    <title>TodoList</title>
</head>
<body>
<form action="todo/read" method="get">
    <ul>
        <c:forEach items="${dtoList}" var="dto">
            <li><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a>${dto}</li>
        </c:forEach>


    </ul>



</form>



</body>
</html>
