<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Productions</title>
    <style> table,th,td{
        width: 900px;
        height:30px;
        border: solid 1px silver;
        text-align:center;
        border-collapse: collapse;
    }
    </style>
</head>
<body>
<%@ include file="header.jsp"%>
<%@ include file="return.jsp"%>
<div>
    <c:if test="${not empty requestScope.sets}">
        <h2>His released sets: </h2>
        <table>
            <thead>
            <tr><th>Name of set</th><th>Made sets</th><th>Date of production</th><tr>
            </thead>
            <tbody>
            <c:forEach var="set" items="${requestScope.sets}">
                <tr><td>${set.set.nameOfSet}</td>
                    <td>${set.madeSets}</td>
                    <td>${set.dateOfProduction}</td>
                    <td><a href='./deleteUserProductionAdmin?id=${set.id}'>Delete</a></td>
                    <td><a href='./updateUserProductionAdmin?id=${set.id}'>Update</a></td></tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div><br/>
<div>
    <form action="${pageContext.request.contextPath}/saveUserProductionAdmin">
        <button type="submit">Add a note</button>
    </form>
</div>
</body>
</html>
