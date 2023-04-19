<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOC TYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello</title>
</head>
<body>
<%@ include file="header.jsp"%>
<p><a href="./login">Hello, ${sessionScope.user.name}</a></p>
<p>${requestScope.worker.description}</p>
<h2>Your sets is: </h2>
<ul>
    <c:if test="${not empty requestScope.sets}">
        <c:forEach var="set" items="${requestScope.sets}">
            <li>${fn:toLowerCase(set.description)}</li>
        </c:forEach>
    </c:if>
</ul>
<h2>Your released sets is: </h2>
<ul>
    <c:if test="${not empty requestScope.sets}">
        <c:forEach var="product" items="${requestScope.released}">
            <li>${fn:toLowerCase(product.description)}</li>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>