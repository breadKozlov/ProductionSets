<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOC TYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello</title>
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
<p>Hello, ${sessionScope.user.name}</p>
<p>${requestScope.description}</p>
<h2>Your planned sets: </h2>
<table>
   <thead>
      <tr><th>Name of set</th><th>Plan</th></tr>
   </thead>
   <tbody>
   <c:if test="${not empty requestScope.workersSets}">
           <c:forEach var="workersSet" items="${requestScope.workersSets}">
               <tr><td>${workersSet.set.nameOfSet}</td>
               <td>${workersSet.requirement}</td>
               <td><a href='./deleteWorkersSets?id=${workersSet.id}'>Delete</a></td>
               <td><a href='./updateWorkersSets?id=${workersSet.id}'>Update</a></td></tr>
           </c:forEach>
   </c:if>
   </tbody>
</table></br>
<form action='./saveWorkersSets'>
    <button type="submit">Add a note</button>
</form>
<h2>Your released sets: </h2>
<table>
      <thead>
           <tr><th>Name of set</th><th>Made sets</th><th>Date of production</th><tr>
      </thead>
      <tbody>
           <c:if test="${not empty requestScope.releasedSets}">
                   <c:forEach var="set" items="${requestScope.releasedSets}">
                       <tr><td>${set.set.nameOfSet}</td>
                       <td>${set.madeSets}</td>
                       <td>${set.dateOfProduction}</td>
                       <td><a href='./deleteProductionWorker?id=${set.id}'>Delete</a></td>
                       <td><a href='./updateProductionWorker?id=${set.id}'>Update</a></td></tr>
                   </c:forEach>
           </c:if>
      </tbody>
</table></br>

<form action='./saveProductionWorker'>
    <button type="submit">Add a note</button>
</form>

<div>
    <c:if test="${not empty sessionScope.foreman}">
        <form action="${pageContext.request.contextPath}/materialsProductionUser">
            <button type="submit">Go to release report</button>
        </form>
    </c:if>
</div>
</body>
</html>