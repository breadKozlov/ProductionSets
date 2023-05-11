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
<h2>Plan materials: </h2>
<table>
  <thead>
  <tr><th>Name of material</th><th>Required</th><th>Released</th>
    <th>Difference</th><tr>
  </thead>
  <tbody>
  <c:if test="${not empty requestScope.materials}">
    <c:forEach var="material" items="${requestScope.materials}">
      <tr><td>${material.nameOfProduct}</td>
        <td>${material.requiredQuantity}</td>
        <td>${material.releasedQuantity}</td>
        <td>${material.difference()}</td></tr>
    </c:forEach>
  </c:if>
  </tbody>
</table>
<h2>Plan sets: </h2>
<table>
  <thead>
  <tr><th>Name of set</th><th>Required</th><th>Released</th>
    <th>Difference</th><tr>
  </thead>
  <tbody>
  <c:if test="${not empty requestScope.sets}">
    <c:forEach var="set" items="${requestScope.sets}">
      <tr><td>${set.nameOfProduct}</td>
        <td>${set.requiredQuantity}</td>
        <td>${set.releasedQuantity}</td>
        <td>${set.difference()}</td></tr>
    </c:forEach>
  </c:if>
  </tbody>
</table>
</body>
</html>