<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <link href="${pageContext.servletContext.contextPath}/static/css/login.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.servletContext.contextPath}/static/css/common.css" rel="stylesheet" type="text/css">
    <title><fmt:message key="registration"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
    <form action="<c:url value="/registration"/>" method="post" class="form">
        <div class="container">
            <label for="username" class="label"><fmt:message key="username"/></label>
            <input type="text" id="username" name="username" class="input"/>
        </div>
        <div class="container">
            <label for="password" class="label"><fmt:message key="password"/></label>
            <input type="password" id="password" name="password" class="input"/>
        </div>
        <div class="container">
            <label for="re_password" class="label"><fmt:message key="re_password"/></label>
            <input type="password" id="re_password" name="re_password" class="input"/>
        </div>
        <button type="submit">
            <fmt:message key="sign_up"/>
        </button>
        <c:choose>
            <c:when test="${sessionScope.reg_message eq 1}" >
                <c:remove var="reg_message" scope="session"/>
            </c:when>
            <c:when test="${sessionScope.reg_message eq 2}">
                <div id="reg-message" class="reg-message reg-invalid_data"><fmt:message key="invalid_data"/></div>
                <c:remove var="reg_message" scope="session"/>
            </c:when>
            <c:when test="${sessionScope.reg_message eq 3}">
                <div id="reg-message" class="reg-message reg-user_exists"><fmt:message key="user_does_not_exist"/></div>
                <c:remove var="reg_message" scope="session"/>
            </c:when>
        </c:choose>
        <div class="link">
            <span><fmt:message key="have_account"/>?</span>
            <a href="<c:url value="/login-form"/>"><fmt:message key="sign_in"/></a>
        </div>
    </form>
</body>
</html>
