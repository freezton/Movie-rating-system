<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <title><fmt:message key="admin.page"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="wrapper">
<%--    <%@ include file="/header.jsp" %>--%>
    <main class="content">
        <a class="back" href="<c:url value="/movies"/>">BACK</a>
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="username"/></th>
                <th><fmt:message key="status"/></th>
                <th><fmt:message key="is_banned"/></th>
            </tr>
            </thead>
            <tbody>
            <c:set value='${sessionScope["usersList"]}' var="usersList"/>
            <c:forEach var="userInfo" items="${usersList}">
                <tr>
                    <td>${userInfo.getUsername()}</td>
                    <td>
                        <div class="rating-wrapper">
                            <form action="<c:url value="/update-user"/>" method="POST">
                                <input type="hidden" name="userId" value="${userInfo.getId()}">
                                <input type="hidden" name="status" value="${userInfo.getStatus() - 1}">
                                <input type="hidden" name="ban" value="${userInfo.isBanned()}">
                                <button class="rating-operation" type="submit">-</button>
                            </form>
                                ${userInfo.getStatus()}
                            <form action="<c:url value="/update-user"/>" method="POST">
                                <input type="hidden" name="userId" value="${userInfo.getId()}">
                                <input type="hidden" name="status" value="${userInfo.getStatus() + 1}">
                                <input type="hidden" name="ban" value="${userInfo.isBanned()}">
                                <button class="rating-operation" type="submit">+</button>
                            </form>
                        </div>
                    </td>
                    <td style="width: 10%; text-align: center">
                        <form action="<c:url value="/update-user"/>" method="POST">
                            <input type="hidden" name="userId" value="${userInfo.getId()}">
                            <input type="hidden" name="status" value="${userInfo.getStatus()}">
                            <input type="hidden" name="ban" value="${!userInfo.isBanned()}">
                            <button class="ban" type="submit">${userInfo.isBanned() ? "Unban" : "Ban"}</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
</div>
</body>
</html>
