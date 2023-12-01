<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <link href="${pageContext.servletContext.contextPath}/static/css/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.servletContext.contextPath}/static/css/common.css" rel="stylesheet" type="text/css">
    <title><fmt:message key="login"/></title>
</head>
<body>
<c:choose>
    <c:when test="${not empty sessionScope.username}">
        <c:redirect url="/movies" />
    </c:when>
    <c:otherwise>
        <jsp:include page="header.jsp"/>
    <form action="<c:url value="/login"/>" method="post" class="form">
        <div class="container">
            <label for="username" class="label"><fmt:message key="username"/></label>
            <input type="text" id="username" name="username" class="input"/>
        </div>
        <div class="container">
            <label for="password" class="label"><fmt:message key="password"/></label>
            <input type="password" id="password" name="password" class="input"/>
        </div>
        <button type="submit">
            <fmt:message key="login"/>
        </button>
        <c:choose>
            <c:when test="${sessionScope.login_message eq 1}">
                <div id="login-message" class="login-message login-user_does_not_exist"><fmt:message key="user_does_not_exist"/></div>
                <c:remove var="login_message" scope="session"/>
            </c:when>
            <c:when test="${sessionScope.login_message eq 2}">
                <div id="login-message" class="login-message login-invalid_credentials"><fmt:message key="invalid_credentials"/></div>
                <c:remove var="login_message" scope="session"/>
            </c:when>
        </c:choose>
        <div class="link">
            <span><fmt:message key="no_account"/>?</span>
            <a href="<c:url value="/registration-form"/>"><fmt:message key="sign_up"/></a>
        </div>
    </form>
    </c:otherwise>
</c:choose>
</body>
</html>
