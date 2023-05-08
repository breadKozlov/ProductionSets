<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Materials production</title>
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
<h2>Materials production: </h2>
<table>
    <thead>
    <tr><th>Name of material</th><th>Name of brigade</th><th>Quantity</th>
        <th>Date of production</th><tr>
    </thead>
    <tbody>
    <c:if test="${not empty requestScope.materials}">
        <c:forEach var="material" items="${requestScope.materials}">
            <tr><td>${material.material.nameOfMaterial}</td>
                <td>${material.brigade.nameOfBrigade}</td>
                <td>${material.quantity}</td>
                <td>${material.dateOfProduction}</td>
                <td><a href='./deleteMaterialsProductionAdmin?id=${material.id}'>Delete</a></td>
                <td><a href='./updateMaterialsProductionAdmin?id=${material.id}'>Update</a></td></tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<br/>
<div>
    <form action="${pageContext.request.contextPath}/saveMaterialsProductionAdmin">
        <button type="submit">Add a note</button>
    </form>
</div>
</body>
</html>