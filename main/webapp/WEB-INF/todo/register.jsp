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
<form action="/todo/register" method="post">
    <input type="text" placeholder="INSERT TITLE" name="title">
    <BR>
    <input type="date" name="duedate">
    <br>
    <button type="reset">RESET</button><button TYPE="submit">REGISTER</button>



</form>
</body>
</html>
