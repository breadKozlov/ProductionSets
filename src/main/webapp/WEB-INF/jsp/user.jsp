<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

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
      <tr><th>Name of set</th><th>Plan</th><th>Unit cost</th><th>Planed total cost</th></tr>
   </thead>
   <tbody>
   <c:if test="${not empty requestScope.workersSets}">
       <c:set var="totalWorker" value="${0}" />
           <c:forEach var="workersSet" items="${requestScope.workersSets}">
               <tr><td>${workersSet.set.nameOfSet}</td>
               <td>${workersSet.requirement}</td>
                   <fmt:formatNumber var="outRateReq" value="${workersSet.set.rateOfSet}" maxFractionDigits="2"/>
                   <td>${outRateReq}</td>
                   <c:set var="allCost" value="${workersSet.set.rateOfSet * workersSet.requirement}"/>
                   <fmt:formatNumber var="outCost" value="${allCost}" maxFractionDigits="2"/>
                   <td>${outCost}</td>
                   <c:set var="totalWorker" value="${totalWorker + allCost}" />
               <td><a href='./deleteWorkersSets?id=${workersSet.id}'>Delete</a></td>
               <td><a href='./updateWorkersSets?id=${workersSet.id}'>Update</a></td></tr>
           </c:forEach>
       <fmt:formatNumber var="outTotalWorker" value="${totalWorker}" maxFractionDigits="2"/>
       <tr><td colspan="3"><b>Total sum:</b></td><td><b>${outTotalWorker}</b></td></tr>
   </c:if>
   </tbody>
</table></br>
<form action='./saveWorkersSets'>
    <button type="submit">Add a note</button>
</form>
<h2>Your released sets: </h2>
<table>
      <thead>
           <tr><th>Name of set</th><th>Made sets</th><th>Date of production</th><th>Unit cost</th><th>Total released cost</th><tr>
      </thead>
      <tbody>
           <c:if test="${not empty requestScope.releasedSets}">
               <c:set var="total" value="${0}" />
                   <c:forEach var="set" items="${requestScope.releasedSets}">
                       <tr><td>${set.set.nameOfSet}</td>
                       <td>${set.madeSets}</td>
                       <td>${set.dateOfProduction}</td>
                           <fmt:formatNumber var="outRate" value="${set.set.rateOfSet}" maxFractionDigits="2"/>
                           <td>${outRate}</td>
                           <c:set var="all" value="${set.madeSets * set.set.rateOfSet}"/>
                           <fmt:formatNumber var="out" value="${all}" maxFractionDigits="2"/>
                           <td>${out}</td>
                           <c:set var="total" value="${total + all}" />
                       <td><a href='./deleteProductionWorker?id=${set.id}'>Delete</a></td>
                       <td><a href='./updateProductionWorker?id=${set.id}'>Update</a></td></tr>
                   </c:forEach>
               <fmt:formatNumber var="outTotal" value="${total}" maxFractionDigits="2"/>
               <tr><td colspan="4"><b>Total sum:</b></td><td><b>${outTotal}</b></td></tr>
           </c:if>
      </tbody>
</table></br>

<form action='./saveProductionWorker'>
    <button type="submit">Add a note</button>
</form>

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
</table><br/>

<div>
    <c:if test="${not empty sessionScope.foreman}">
        <form action="${pageContext.request.contextPath}/materialsProductionUser">
            <button type="submit">Go to release report</button>
        </form>
    </c:if>
</div>
</body>
</html>