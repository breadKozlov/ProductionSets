<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="./saveProduction" method="post">
    <select name="worker" id="worker">
        <c:forEach var="worker" items="${requestScope.workers}">
            <option label="${worker.id}">${worker.nameOfWorker} ${worker.surnameOfWorker}</option><br>
        </c:forEach>
    </select><br/>
    <select name="set" id="set">
            <c:forEach var="set" items="${requestScope.sets}">
                <option label="${set.id}">${set.nameOfSet}</option><br>
            </c:forEach>
        </select><br/>
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