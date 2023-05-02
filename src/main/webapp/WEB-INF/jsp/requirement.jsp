<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Requirement</title>
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
<h2>Requirement: </h2>
<table>
    <thead>
    <tr><th>Name of set</th><th>Name of material</th><th>Cost of unit</th>
        <th>Total sets</th><tr>
    </thead>
    <tbody>
    <c:if test="${not empty requestScope.requirements}">
        <c:forEach var="requirement" items="${requestScope.requirements}">
            <tr><td>${requirement.set.nameOfSet}</td>
                <td>${requirement.material.nameOfMaterial}</td>
                <td>${requirement.unitCost}</td>
                <td>${requirement.totalSets}</td>
                <td><a href='./deleteRequirement?id=${requirement.id}'>Delete</a></td>
                <td><a href='./updateRequirement?id=${requirement.id}'>Update</a></td></tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<br/>
<div>
    <form action="${pageContext.request.contextPath}/saveRequirement">
        <button type="submit">Add a note</button>
    </form>
</div>
</body>
</html>