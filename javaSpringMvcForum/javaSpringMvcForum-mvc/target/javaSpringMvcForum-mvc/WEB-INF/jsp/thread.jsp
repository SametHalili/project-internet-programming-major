<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="msg" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="escape" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="pageTitle" value="${currThread.threadName} - Blabla Forum"></jsp:param>
</jsp:include>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<c:url value="/index.htm"/>">Home</a></li>
                    <li class="breadcrumb-item"><a href="<c:url value="/forum/${currForum.forumId}.htm"/>"><c:out value="${currForum.forumName}"></c:out></a>
                    </li>
                    <li class="breadcrumb-item active" aria-current="page"><c:out value="${currThread.threadName}"/></li>
                </ol>
            </nav>
            <div class="card">
                <div class="card-header">
                    <h5 class="card-header"><c:out value="${currThread.threadName}"/></h5>
                </div>
                <div class="card-header">
                    <dl class="row">
                        <dt class="col-sm-2"><spring:message code="label.username"/>:</dt>
                        <dd class="col-sm-10"><c:out value="${currThread.usernameOP}"/></dd>

                        <dt class="col-sm-2">Thread ID:</dt>
                        <dd class="col-sm-10">${currThread.threadId}</dd>

                        <dt class="col-sm-2">Time posted:</dt>
                        <dd class="col-sm-10">${currThread.threadCreated}</dd>
                    </dl>
                </div>
                <div class="card-body">
                    <p><c:out value="${currThread.msgOP}"/></p>
                </div>
            </div>
            <c:choose>
                <c:when test="${empty currThread.forumPostList}">

                </c:when>
                <c:otherwise>
                    <c:forEach items="${currThread.forumPostList}" var="post">
                        <div class="card">
                            <div class="card-header">
                                <dl class="row">
                                    <dt class="col-sm-2"><spring:message code="label.username"/>:</dt>
                                    <dd class="col-sm-10">
                                            <c:out value="${post.username}"/>
                                    <dd/>

                                    <dt class="col-sm-2">Post ID:</dt>
                                    <dd class="col-sm-10">${post.postId}</dd>

                                    <dt class="col-sm-2">Time posted:</dt>
                                    <dd class="col-sm-10">${post.msgTime}</dd>
                                </dl>
                            </div>
                            <div class="card-body">
                                <p><c:out value="${post.msg}"/></p>
                                <security:authorize access="hasRole('ADMIN')">
                                    <div class="dropdown">
                                        <button class="btn btn-secondary dropdown-toggle" type="button"
                                                id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                                aria-expanded="false">
                                            Actions
                                        </button>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <a class="dropdown-item"
                                               href="<c:url value="/forum/${currThread.forumPostedId}/thread/${currThread.threadId}/edit/${post.postId}.htm"/>"><msg:message
                                                    key="label.edit"/></a>
                                            <a class="dropdown-item"
                                               href="<c:url value="/forum/${currThread.forumPostedId}/thread/${currThread.threadId}/delete/${post.postId}.htm"/>"><msg:message
                                                    key="label.delete"/></a>
                                        </div>
                                    </div>
                                </security:authorize>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <a href="<c:url value="/forum/${currThread.forumPostedId}/thread/${currThread.threadId}/new.htm"/>"
               class="btn btn-primary btn-lg" role="button"><spring:message code="label.postmessage"/></a>
        </div>
        <div class="col-sm-2"></div>
    </div>

</div>
</body>
</html>