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
                <form:form role="form" method="POST" action="${pageContext.request.contextPath}/forum"
                           modelAttribute="newForum">
                    <div>
                        <h1><msg:message key="label.createForum"/></h1>
                        <p class="form-group">
                            <label for="forumName"><spring:message code="label.forumName"/>:</label>
                            <form:input type="text" cssClass="form-control" id="forumName" path="forumName"/>
                        </p>
                        <p><form:errors path="forumName" cssClass="alert alert-warning"/></p>
                        <p class="form-group">
                            <label for="description"><spring:message code="label.description"/>:</label>
                            <form:input type="text" cssClass="form-control" id="description" path="description"/>
                        </p>
                        <p><form:errors path="description" cssClass="alert alert-warning"/></p>
                        <button type="submit" class="btn btn-primary btn-lg" name="createForum" value="createForum"
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
