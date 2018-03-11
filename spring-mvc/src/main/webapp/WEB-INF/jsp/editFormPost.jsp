<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <label for="postId">Post ID:</label>
                <form:input type="text" cssClass="form-control" id="postId"  path="postId" required="required" readonly="true"/>
            </p>
            <p class="form-group">
                <label for="username">Username:</label>
                <form:input type="text" cssClass="form-control" id="username"  path="username" required="required" readonly="true"/>
            </p>
            <p class="form-group">
                <label for="message">Message:</label>
                <form:input type="text" cssClass="form-control" id="message" path="msg" required="required"/>
            </p>
            <p><form:errors path="msg" cssClass="alert alert-warning" /></p>
            <button id="save" type="submit" class="btn btn-default">Submit</button>
        </div>
    </div>
</form:form>

</body>
</html>
