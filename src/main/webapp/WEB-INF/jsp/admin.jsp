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
<p>Hello, ${sessionScope.user.name}</p>
<h2>Workers:</h2>
<ul>
    <c:forEach var="worker" items="${requestScope.workers}">
        <li>
            <a href="./productionEveryUser?id=${worker.id}">${worker.nameOfWorker} ${worker.surnameOfWorker}</a>
        </li>
    </c:forEach>
</ul><br/>
<a href='./productionAdmin'>Production</a><br/>
<a href='./requirement'>Requirement</a><br/>
<a href='./materialsProductionAdmin'>Materials production</a><br/>
<a href='./planProduction'>Plan production</a>
</body>
</html>
