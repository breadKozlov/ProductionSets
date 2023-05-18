<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Title</title>
</head>
<body>
<form action="./saveMaterialsProductionAdmin" method="post">
  <select name="brigadeId" id="brigade">
    <c:forEach var="brigade" items="${requestScope.brigades}">
      <option label="${brigade.nameOfBrigade}" value="${brigade.id}">${brigade.nameOfBrigade}</option><br>
    </c:forEach>
  </select><br/>
  <select name="materialId" id="material">
    <c:forEach var="material" items="${requestScope.materials}">
      <option label="${material.nameOfMaterial}" value="${material.id}" >${material.nameOfMaterial}</option><br>
    </c:forEach>
  </select><br/>
  <label for="quantity">Made material:
    <input type="number" required step="0.5" min="10" max="3000" name="quantity">
  </label><br/>
  <label for="dateOfProduction">Date of production:
    <input type="date" name="dateOfProduction">
  </label><br/>
  <input type="submit" value="Add">
</form>
<c:if test="${not empty requestScope.errors}">
  <div style="color: red">
    <c:forEach var="error" items="${requestScope.errors}">
      <span>${error.getPropertyPath().toString()} ${error.getMessage()}</span>
      <br>
    </c:forEach>
  </div>
</c:if>
</body>
</html>