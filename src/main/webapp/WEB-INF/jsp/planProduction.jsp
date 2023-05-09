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
  <c:if test="${not empty requestScope.productions}">
    <c:forEach var="product" items="${requestScope.productions}">
      <tr><td>${product.nameOfMaterial}</td>
        <td>${product.requiredMaterial}</td>
        <td>${product.releasedMaterial}</td>
        <td>${product.difference()}</td></tr>
    </c:forEach>
  </c:if>
  </tbody>
</table>
</body>
</html>