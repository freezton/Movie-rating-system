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
<div class="">
    <form action="<c:url value="/registration"/>" method="post" class="form">
        <div class="container">
            <label for="username" class="label">Username:</label>
            <input type="text" id="username" name="username" class="input"/>
        </div>
        <div class="container">
            <label for="password" class="label">Password:</label>
            <input type="password" id="password" name="password" class="input"/>
        </div>
        <div class="container">
            <label for="re_password" class="label">Password:</label>
            <input type="password" id="re_password" name="re_password" class="input"/>
        </div>
        <button type="submit">
            Sign up
        </button>
        <div class="link">
            <span>Already have an account?</span>
            <%--            <a href="/controller?page=registration.jsp">Sign up</a>--%>
            <a href="<c:url value="/login-form"/>">Sign in</a>

        </div>
    </form>
</div>
</body>
</html>
