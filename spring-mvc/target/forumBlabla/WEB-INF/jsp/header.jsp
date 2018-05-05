<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="jumbotron text-center">
    <h1 class="display-1">Blabla Forum</h1>
    <h2>Welcome to the forum!</h2>
    <c:if test="${empty user.username}">
        <a href="/login.htm" class="btn btn-primary btn-lg" role="button"><spring:message code="label.login"/></a>
        <a href="/register.htm" class="btn btn-primary btn-lg" role="button"><spring:message code="label.register"/></a>
    </c:if>
    <c:if test="${!empty user.username}">
        <a href="/login/logout.htm" class="btn btn-primary btn-lg" role="button">Logout</a>
        <a href="/user/profile.htm" class="btn btn-primary btn-lg" role="button">Profile</a>
    </c:if>
</div>