<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:htmlEscape defaultHtmlEscape="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="pageTitle" value="Index - Blabla Forum"></jsp:param>
</jsp:include>
<body>
<jsp:include page="header.jsp"></jsp:include>

<form:form role="form" method="POST" action="${pageContext.request.contextPath}/thread.htm" modelAttribute="forumPost">
    <div class="row">
        <div class="col-sm-2">
        </div>
        <div class="col-sm-2">
            <p class="form-group">
                <label for="username">Username:</label>
                <form:input type="text" cssClass="form-control" id="username" path="username"/>
            </p>
            <p><form:errors path="username" cssClass="alert alert-warning"/></p>
            <p class="form-group">
                <label for="message">Message:</label>
                <form:input type="text" cssClass="form-control" id="message" path="msg"/>
            </p>
            <p><form:errors path="msg" cssClass="alert alert-warning" /></p>
            <button type="submit" class="button" name="addPost" value="AddPost" class="btn btn-default">Submit</button>
            <button type="submit" class="button" name="cancel" value="Cancel" class="btn btn-default">Cancel</button>
        </div>
    </div>
</form:form>

</body>
</html>
