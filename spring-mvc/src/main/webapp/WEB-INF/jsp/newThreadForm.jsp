<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="msg" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:htmlEscape defaultHtmlEscape="true"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="pageTitle" value="Index - Blabla Forum"></jsp:param>
</jsp:include>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="card col-sm-6 offset-sm-3">
            <div class="card-body">
                <form:form role="form" method="POST" action="${pageContext.request.contextPath}/forum.htm"
                           modelAttribute="newThread">
                    <div>
                        <h1><msg:message key="label.createThread"/></h1>
                        <p class="form-group">
                            <label for="forumPostedId">Forum ID:</label>
                            <form:input type="text" cssClass="form-control" id="forumPostedId" path="forumPostedId"
                                        readonly="true"/>
                        </p>
                        <p class="form-group">
                            <label for="threadName"><spring:message code="label.threadName"/>:</label>
                            <form:input type="text" cssClass="form-control" id="threadName" path="threadName"/>
                        </p>
                        <p><form:errors path="threadName" cssClass="alert alert-warning"/></p>
                        <p class="form-group">
                            <label for="username"><spring:message code="label.username"/>:</label>
                            <form:input type="text" cssClass="form-control" id="username" path="usernameOP"/>
                        </p>
                        <p><form:errors path="usernameOP" cssClass="alert alert-warning"/></p>
                        <p class="form-group">
                            <label for="msg"><spring:message code="label.message"/>:</label>
                            <form:input type="text" cssClass="form-control" id="msg" path="msgOP"/>
                        </p>
                        <p><form:errors path="msgOP" cssClass="alert alert-warning"/></p>
                        <button type="submit" class="btn btn-primary btn-lg" name="createThread" value="CreateThread"
                                class="btn btn-default"><spring:message code="label.submit"/></button>
                        <button type="submit" class="btn btn-primary btn-lg" name="cancel" value="Cancel"
                                class="btn btn-default">
                            <spring:message code="label.cancel"/></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
