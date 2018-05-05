<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="pageTitle" value="Index - Blabla Forum"></jsp:param>
</jsp:include>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <c:choose>
                <c:when test="${empty thread}">
                    <p>Thread empty</p>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${thread}" var="post">
                        <div class="card">
                            <div class="card-header">
                                <p>${post.username}</p>
                                <p>Time posted: ${post.msgTimeFormatted}</p>
                                <p>Post id: ${post.postId}</p>
                            </div>
                            <div class="card-body">${post.msg}</div>
                        </div>
                        <a href="<c:url value="/thread/edit/${post.postId}.htm"/>" class="btn btn-primary btn-lg" role="button">Edit</a>
                        <a href="<c:url value="/thread/delete/${post.postId}.htm"/>" class="btn btn-primary btn-lg" role="button">Delete</a>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-sm-2"></div>
        <a href="<c:url value="/thread/new.htm"/>" class="btn btn-primary btn-lg" role="button"><spring:message code="label.postmessage"/></a>
    </div>

</div>
</body>
</html>