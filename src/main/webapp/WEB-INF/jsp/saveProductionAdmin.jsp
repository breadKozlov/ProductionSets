<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="./saveProductionAdmin" method="post">
    <select name="workerId" id="worker">
        <c:forEach var="worker" items="${requestScope.workers}">
            <option label="${worker.nameOfWorker}" value="${worker.id}">${worker.nameOfWorker} ${worker.surnameOfWorker}</option><br>
        </c:forEach>
    </select><br/>
    <select name="setId" id="set">
            <c:forEach var="set" items="${requestScope.sets}">
                <option label="${set.nameOfSet}" value="${set.id}" >${set.nameOfSet}</option><br>
            </c:forEach>
    </select><br/>
    <label for="madeSets">Made sets:
            <input type="number" required step="1" min="10" max="1000" name="madeSets">
    </label><br/>
    <label for="dateOfProduction">Date of production:
            <input type="date" name="dateOfProduction">
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