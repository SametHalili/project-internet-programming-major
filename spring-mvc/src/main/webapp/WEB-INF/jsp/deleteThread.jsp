<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="msg" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:htmlEscape defaultHtmlEscape="true"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="pageTitle" value="Delete thread - Blabla Forum"></jsp:param>
</jsp:include>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="card col-sm-6 offset-sm-3">
            <div class="card-body">
                <form:form role="form" method="POST" action="${pageContext.request.contextPath}/forum"
                           modelAttribute="toBeDeletedThread">
                    <div>
                        <h1><msg:message key="label.deleteFollowing"/></h1>
                        <p class="form-group">
                            <label for="forumPostedId">Forum ID:</label>
                            <form:input type="text" cssClass="form-control" id="forumPostedId" path="forumPostedId"
                                        readonly="true"/>
                        </p>
                        <p class="form-group">
                            <label for="postId">Thread name:</label>
                            <form:input type="text" cssClass="form-control" id="postId" path="threadName"
                                        readonly="true"/>
                        </p>
                        <p class="form-group">
                            <label for="threadId">Thread ID:</label>
                            <form:input type="text" cssClass="form-control" id="threadId" path="threadId"
                                        readonly="true"/>
                        </p>
                        <p class="form-group">
                            <label for="username">Username:</label>
                            <form:input type="text" cssClass="form-control" id="username" path="usernameOP"
                                        readonly="true"/>
                        </p>
                        <p class="form-group">
                            <label for="message">Message:</label>
                            <form:input type="text" cssClass="form-control" id="message" path="msgOP" readonly="true"/>
                        </p>
                        <p>
                            <button type="submit" class="btn btn-primary btn-lg" name="deleteThread"
                                    value="DeleteThread" class="btn btn-default"><spring:message
                                    code="label.delete"/></button>
                            <button type="submit" class="btn btn-primary btn-lg" name="cancel" value="Cancel"
                                    class="btn btn-default"><spring:message code="label.cancel"/></button>
                        </p>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
