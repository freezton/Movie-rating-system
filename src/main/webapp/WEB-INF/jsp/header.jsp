<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="language"/>

<header>
<%--  <a href="<c:url value="/movies"/>"><fmt:message key="movies"/> </a>--%>
  <c:choose>
    <c:when test="${not empty sessionScope.username}">

      <c:choose>
        <c:when test="${sessionScope.role eq 'ADMIN'}">
          <a href="<c:url value="/get-users"/>"><fmt:message key="admin.users"/></a>
        </c:when>
      </c:choose>

      <a href="<c:url value='/logout'/>"><fmt:message key="logout"/></a>
    </c:when>
<%--    <c:otherwise>--%>
<%--      <a href="<c:url value='/login-form'/>">${login}</a>--%>
<%--      <a href="<c:url value='/registration-form'/>">${registration}</a>--%>
<%--    </c:otherwise>--%>
  </c:choose>

  <a href="<c:url value="/change-locale?locale=ru"/>">Русский</a>
  <a href="<c:url value="/change-locale?locale=en"/>">English</a>
  <br>
</header>