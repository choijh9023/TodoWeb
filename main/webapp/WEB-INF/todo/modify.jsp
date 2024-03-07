<%--
  Created by IntelliJ IDEA.
  User: choij
  Date: 2024-03-07
  Time: 오후 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/todo/modify" method="post">
    <input type="text" name="tno" value="${requestScope.dto.tno}"readonly>
    <br>
    <input type="text" name="title" value="${requestScope.dto.title}">
    <br>
    <input type="text" name="dueDate" value="${requestScope.dto.dueDate}">
    <br>
    <input type="checkbox" name="finished" ${requestScope.dto.finished ? 'checked' : ''}>
    <br>
    <button type="submit">Modify</button>
    <br>
</form>

<form action="/todo/remove" method="post">
    <input type="hidden" name="tno" value="${requestScope.dto.tno}">
    <button type="submit">REMOVE</button>





</form>
</body>
</html>
