<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <link href="${pageContext.servletContext.contextPath}/static/css/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.servletContext.contextPath}/static/css/common.css" rel="stylesheet" type="text/css">
    <title>Login</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty sessionScope.username}">
        <c:redirect url="/movies" />
    </c:when>
    <c:otherwise>
    <form action="<c:url value="/login"/>" method="post" class="form">
        <div class="container">
            <label for="username" class="label">Username:</label>
            <input type="text" id="username" name="username" class="input"/>
        </div>
        <div class="container">
            <label for="password" class="label">Password:</label>
            <input type="password" id="password" name="password" class="input"/>
        </div>
        <button type="submit">
            Login
        </button>
        <div class="link">
            <span>No account?</span>
            <a href="<c:url value="/registration-form"/>">Sign up</a>
        </div>
    </form>
    </c:otherwise>
</c:choose>
</body>
</html>
