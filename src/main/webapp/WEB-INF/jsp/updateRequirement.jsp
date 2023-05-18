<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<form action="./updateRequirement" method="post">
  <input type="hidden" name="id" value="${requestScope.id}">
  <label for="totalSets">Planed sets:
    <input type="number" required step="1" min="0" max="1000" name="totalSets">
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
