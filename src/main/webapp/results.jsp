<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/18/20
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ include file="template/template-head.jsp"%>
</head>
<body>
<h2>Search Results: </h2>
<c:set var="recipes" value="${recipes}"/>
<c:if test="${not empty recipes}">
    <div class="card">
        <c:forEach var ="recipe" items="${recipes}">
            <tr><td>${recipe.id}</td><td>${recipe.name}</td>
                <td>${recipe.description}</td></tr>
        </c:forEach>
    </div>
</c:if>
<c:if test="${empty recipes}">
    Sorry, there were no results found
</c:if>

<a href="index.jsp">Try Again!</a>
</body>
</html>
