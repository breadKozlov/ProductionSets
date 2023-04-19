<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Productions</title>
</head>
<body>
<%@ include file="header.jsp"%>
<div>
    <c:if test="${not empty requestScope.sets}">
        <h2>His released sets: </h2>
        <ul>
        <c:forEach var="set" items="${requestScope.sets}">
             <li>${fn:toLowerCase(set.getDescription())}</li>
        </c:forEach>
        </ul>
    </c:if>
</div>
<h2>Production: </h2>
<ul>
    <c:if test="${not empty requestScope.productions}">
        <c:forEach var="product" items="${requestScope.productions}">
            <li>${fn:toLowerCase(product.getDescription())}</li>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>