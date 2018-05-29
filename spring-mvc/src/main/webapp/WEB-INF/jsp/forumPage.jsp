<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="msg" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/index">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">${currForum.forumName}</li>
                </ol>
            </nav>
            <h2>${currForum.forumName}</h2>
            <p>${currForum.description}</p>
            <div class="card">
                <div class="card-header">
                    <div class="row">
                        <div class="col-sm-6">
                            <h5 class="card-header"><msg:message key="label.listOfThread"/></h5>
                        </div>
                        <div class="col-sm-2">
                            <a href="<c:url value="/forum/${currForum.forumId}/newThread.htm"/>" class="btn btn-primary btn-lg" role="button"><spring:message code="label.createThread"/></a>
                        </div>
                    </div>
                </div>
                <div class="list-group">
                    <c:choose>
                        <c:when test="${empty currForum.threadList}">
                            <p>No threads in this forum at the moment!</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${currForum.threadList}" var="thread">
                                <div class="list-group-item">
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <a href="/forum/${currForum.forumId}/thread/${thread.threadId}.htm">${thread.threadName}</a>
                                        </div>
                                        <div class="col-sm-2">
                                            <div class="dropdown">
                                                <button class="btn btn-secondary dropdown-toggle" type="button"
                                                        id="dropdownMenuButton" data-toggle="dropdown"
                                                        aria-haspopup="true" aria-expanded="false">
                                                    Actions
                                                </button>
                                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                    <a class="dropdown-item"
                                                       href="<c:url value="/forum/${currForum.forumId}/thread/${thread.threadId}/editThread.htm"/>">Edit</a>
                                                    <a class="dropdown-item"
                                                       href="<c:url value="/forum/${currForum.forumId}/thread/${thread.threadId}/deleteThread.htm"/>">Delete</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-2"></div>
</div>
</body>
</html>
