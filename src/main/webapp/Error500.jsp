<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 5/12/20
  Time: 2:32 AM
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
        500 Error
    </div>
</div>

<div class="container">
    <div class="row">
        <h2 class="text-center">There was an error on the backend. Please try again later.</h2>
        <img id="500Error" src="img/500.jpg">
    </div>
</div>

</body>
</html>
