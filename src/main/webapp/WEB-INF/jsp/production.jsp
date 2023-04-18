<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Productions</title>
</head>
<body>
<%@ include file="header.jsp"%>

<h1>Released sets: </h1>
<ul>
    <c:if test="${not empty requestScope.sets}">
        <c:forEach var="set" items="${requestScope.sets}">
            <li>${fn:toLowerCase(set.nameOfSets)}</li>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>