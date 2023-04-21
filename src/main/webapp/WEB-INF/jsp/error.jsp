<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOC TYPE html>
<html>
<body>
<h2>Oops... ${message}</h2>
<p></a></p>
<div>
    <c:if test="${not empty sessionScope.user}">
        <form action="${pageContext.request.contextPath}/return" method="post">
            <button type="submit">Back</button>
        </form>
    </c:if>
</div>
</body>
</html>