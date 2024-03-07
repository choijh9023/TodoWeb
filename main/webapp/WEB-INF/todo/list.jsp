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
<title>TodoList</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }

    form {
        max-width: 600px;
        margin: 20px auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        border-bottom: 1px solid #ddd;
        padding: 10px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    a {
        text-decoration: none;
        color: #0066cc; /* Change to your preferred blue color */
        font-weight: bold;
    }

    a:hover {
        color: #004080; /* Change to a darker shade for hover effect */
    }

</style>
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