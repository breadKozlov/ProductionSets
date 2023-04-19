<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Materials production</title>
</head>
<body>
<h2>Materials production: </h2>
<ul>
    <c:if test="${not empty requestScope.materials}">
        <c:forEach var="material" items="${requestScope.materials}">
            <li>${fn:toLowerCase(requirement.description)}</li>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>