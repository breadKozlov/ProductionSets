<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Requirement</title>
</head>
<body>
<h2>Requirement: </h2>
<ul>
    <c:if test="${not empty requestScope.requirements}">
        <c:forEach var="requirement" items="${requestScope.requirements}">
            <li>${fn:toLowerCase(requirement.description)}</li>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>