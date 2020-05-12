<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/12/20
  Time: 2:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <%@ include file="template/head.jsp"%>
</head>
<body>
<div class="header">
    <%@ include file="template/navbar.jsp"%>
    <div class="display-4 text-center">
        404 Error
    </div>
</div>

<div class="container">
    <div class="row">
        <h2 class="col-4">The page you were trying to access could not be found. Please make sure you've entered all
            information correctly and please try again.</h2>
        <img class="col-6" src="img/404.jpg">
    </div>
</div>
</body>
</html>
