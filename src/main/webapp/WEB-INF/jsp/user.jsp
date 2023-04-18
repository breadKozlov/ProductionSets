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
<p><a href="./login">Hello, ${sessionScope.user.name}</a></p></br>
<p>${requestScope.worker.description}</p></br>
<h1>Your sets is: </h1>
<ul>
    <c:if test="${not empty requestScope.sets}">
        <c:forEach var="set" items="${requestScope.sets}">
            <li>${fn:toLowerCase(set.description)}</li>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>