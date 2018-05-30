<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="escape" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="msg" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<escape:htmlEscape defaultHtmlEscape="false" />
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
                    <li class="breadcrumb-item active" aria-current="page">Home</li>
                </ol>
            </nav>
            <div class="row">
                <div class="card-body col-sm-8">
                    <h4><spring:message key="label.currentNews"/></h4>
                    <div class="accordion" id="accordionExample">
                        <c:forEach items="${headlines}" var="article" begin="0" end="2" varStatus="count">
                            <div class="card">
                                <div class="card-block" id="heading${count.index}">
                                    <h6>
                                        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${count.index}" aria-controls="collapse${count.index}">
                                            <h6>${article.title}</h6>
                                        </button>
                                    </h6>
                                </div>

                                <div id="collapse${count.index}" class="collapse" aria-labelledby="heading${count.index}" data-parent="#accordionExample">
                                    <div class="card-body">
                                        <p><c:choose>
                                            <c:when test="${empty article.description}">
                                                Not found
                                            </c:when>
                                            <c:otherwise>
                                                ${article.description}
                                            </c:otherwise>
                                        </c:choose>
                                        </p>
                                        <h6>Author:<c:choose>
                                            <c:when test="${empty article.author}">
                                                Not found
                                            </c:when>
                                            <c:otherwise>
                                                ${article.author}
                                            </c:otherwise>
                                        </c:choose>
                                        </h6>
                                        <h6>Source: ${article.source.name}</h6>
                                        <a class="btn btn-primary" href="${article.url}" role="button">Go to article</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-2">
        </div>
        <div class="col-sm-8">
            <h2>Forums</h2>
            <div class="card">
                <div class="card-header">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-8">
                                <h5 class="card-header"><msg:message key="label.listOfForums"/></h5>
                            </div>
                            <div class="col-sm-2">
                                <security:authorize access="hasRole('ADMIN')">
                                    <a class="btn btn-primary" href="/forum/newForum.htm" role="button"><msg:message key="label.createForum"/></a>
                                </security:authorize>
                            </div>
                        </div>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${empty forums}">
                        <div class="card-header">
                            <h5><msg:message key="label.noForums"/></h5>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${forums}" var="forum">
                            <div class="card-header">
                                <div class="row">
                                    <div class="col-sm-10">
                                        <h5 class="card-title"><a
                                                href="/forum/${forum.forumId}.htm"><c:out value="${forum.forumName}"/></a></h5>
                                        <h6 class="card-subtitle"><c:out value="${forum.description}"/></h6>
                                    </div>
                                    <div class="col-sm-2">
                                        <security:authorize access="hasRole('ADMIN')">
                                            <button class="btn btn-secondary dropdown-toggle" type="button"
                                                    id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                                    aria-expanded="false">
                                                Actions
                                            </button>
                                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                <a class="dropdown-item"
                                                   href="/forum/${forum.forumId}/editForum.htm">
                                                    <spring:message key="label.edit"/>
                                                </a>
                                                <a class="dropdown-item" href="/forum/${forum.forumId}/deleteForum.htm">
                                                    <spring:message key="label.delete"/>
                                                </a>
                                            </div>
                                        </security:authorize>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
            <p></p>
        </div>
        <div class="col-sm-2">
        </div>
    </div>
</div>
</body>
</html>
