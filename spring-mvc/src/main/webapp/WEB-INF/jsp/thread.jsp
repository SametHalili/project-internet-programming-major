<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="msg" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <li class="breadcrumb-item"><a href="/index">Home</a></li>
                    <li class="breadcrumb-item"><a href="/forum/${currForum.forumId}.htm">${currForum.forumName}</a>
                    </li>
                    <li class="breadcrumb-item active" aria-current="page">${currThread.threadName}</li>
                </ol>
            </nav>
            <div class="card">
                <div class="card-header">
                    <h5 class="card-header">${currThread.threadName} | Posts: ${currThread.forumPostList.size() + 1}</h5>
                </div>
                <div class="card-header">
                    <dl class="row">
                        <dt class="col-sm-2"><spring:message code="label.username"/>:</dt>
                        <dd class="col-sm-10">${currThread.usernameOP}</dd>

                        <dt class="col-sm-2">Thread ID:</dt>
                        <dd class="col-sm-10">${currThread.threadId}</dd>

                        <dt class="col-sm-2">Time posted:</dt>
                        <dd class="col-sm-10">${currThread.threadCreatedFormatted}</dd>
                    </dl>
                </div>
                <div class="card-body">
                    <p>${currThread.msgOP}</p>
                </div>
            </div>
            <c:choose>
                <c:when test="${empty currThread.forumPostList}">
                    <p>Thread empty</p>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${currThread.forumPostList}" var="post">
                        <div class="card">
                            <div class="card-header">
                                <dl class="row">
                                    <dt class="col-sm-2"><spring:message code="label.username"/>:</dt>
                                    <dd class="col-sm-10">
                                        ${post.username}
                                    <dd/>

                                    <dt class="col-sm-2">Post ID:</dt>
                                    <dd class="col-sm-10">${post.postId}</dd>

                                    <dt class="col-sm-2">Time posted:</dt>
                                    <dd class="col-sm-10">${post.msgTimeFormatted}</dd>
                                </dl>
                            </div>
                            <div class="card-body">
                                <p>${post.msg}</p>
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