<%--
  Created by IntelliJ IDEA.
  User: choij
  Date: 2024-03-07
  Time: 오후 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/todo/modify" method="get">
    <input type="text" name="tno" value="${requestScope.dto.tno}" readonly>
    <br>
    <input type="text" name="title" value="${requestScope.dto.title}"readonly>
    <br>
    <input type="text" name="dueDate" value="${requestScope.dto.dueDate}"readonly>
    <br>
    <input type="checkbox" name="finished" ${requestScope.dto.finished ? 'checked' : ''}readonly>
    <br>
    <input type="submit" value="Modify/Remove List">
</form>
</body>

</html>