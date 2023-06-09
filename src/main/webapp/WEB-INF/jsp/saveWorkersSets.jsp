<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="./saveWorkersSets" method="post">
    <select name="setId" id="set">
        <c:forEach var="set" items="${requestScope.sets}">
            <option label="${set.nameOfSet}" value="${set.id}" >${set.nameOfSet}</option><br>
        </c:forEach>
    </select><br/>
    <label for="requirement">Planed requirement:
        <input type="number" required step="1" min="10" max="1000" name="requirement">
    </label><br/>
    <input type="submit" value="Add">
</form>
<c:if test="${not empty requestScope.errors}">
    <div style="color: red">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span>
            <br>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
