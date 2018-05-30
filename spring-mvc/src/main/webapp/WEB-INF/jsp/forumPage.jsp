<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="msg" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
                    <li class="breadcrumb-item active" aria-current="page"><c:out value="${currForum.forumName}"/></li>
                </ol>
            </nav>
            <h2><c:out value="${currForum.forumName}"/></h2>
            <p><c:out value="${currForum.description}"></c:out></p>
            <div class="card">
                <div class="card-header">
                    <div class="row">
                        <div class="col-sm-6">
                            <h5 class="card-header"><msg:message key="label.listOfThread"/></h5>
                        </div>
                        <div class="col-sm-2">
                            <a href="<c:url value="/forum/${currForum.forumId}/newThread.htm"/>"
                               class="btn btn-primary btn-lg" role="button"><spring:message
                                    code="label.createThread"/></a>
                        </div>
                    </div>
                </div>
                <div class="list-group">
                    <c:choose>
                        <c:when test="${empty currForum.threadList}">
                            <p>No threads in this forum at the moment!</p>
                        </c:when>
                        <c:otherwise>
                            <table class="table">
                                <thead class="thead-light">
                                <tr>
                                    <th scope="col-sm-1">ID</th>
                                    <th scope="col-sm-4"><msg:message key="label.replies"/></th>
                                    <th scope="col-sm-2">OP</th>
                                    <th scope="col-sm-1"><msg:message key="label.replies"/></th>
                                    <security:authorize access="hasRole('ADMIN')">
                                        <th scope="col-sm-2"></th>
                                    </security:authorize>
                                </tr>
                                </thead>

                                <c:forEach items="${currForum.threadList}" var="thread">
                                    <tbody>
                                    <tr>
                                        <th scope="row">${thread.threadId}</th>
                                        <td><a href="/forum/${currForum.forumId}/thread/${thread.threadId}.htm"><c:out
                                                value="${thread.threadName}"></c:out></a></td>
                                        <td><c:out value="${thread.usernameOP}"/></td>
                                        <td>${thread.forumPostList.size()}</td>
                                        <security:authorize access="hasRole('ADMIN')">
                                            <td>
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
                                            </td>
                                        </security:authorize>
                                    </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
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
