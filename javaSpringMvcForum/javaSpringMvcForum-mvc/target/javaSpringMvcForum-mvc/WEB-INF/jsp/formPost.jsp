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
    <jsp:param name="pageTitle" value="New Post - Blabla Forum"></jsp:param>
</jsp:include>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="card col-sm-6 offset-sm-3">
            <div class="card-body">
                <form:form role="form" method="POST" action="${pageContext.request.contextPath}/forum/${newForumPost.threadPostedId}/thread.htm"
                           modelAttribute="newForumPost">
                    <div>
                        <h1><msg:message key="label.createPost"/></h1>
                        <p>
                            <label for="threadId">Thread ID:</label>
                            <form:input type="text" cssClass="form-control" id="threadId" path="threadPostedId"
                                        readonly="true"/>
                        </p>
                        <p class="form-group">
                            <label for="username"><spring:message code="label.username"/>:</label>
                            <form:input type="text" cssClass="form-control" id="username" path="username"/>
                        </p>
                        <p><form:errors path="username" cssClass="alert alert-warning"/></p>
                        <p class="form-group">
                            <label for="message"><spring:message code="label.message"/>:</label>
                            <form:textarea type="text" cssClass="form-control" id="message" path="msg"/>
                        </p>
                        <p><form:errors path="msg" cssClass="alert alert-warning"/></p>
                        <p>
                            <button type="submit" class="btn btn-primary btn-lg" name="addPost" value="AddPost"
                                    class="btn btn-default"><spring:message code="label.submit"/></button>
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
