<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="msg" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="pageTitle" value="Index - Blabla Forum"></jsp:param>
</jsp:include>

<body>
<jsp:include page="header.jsp"></jsp:include>
<head>
    <div class="container">
        <div class="row">
            <div class="card col-sm-6 offset-sm-3">
                <div class="card-body">
                    <form name='f' action="${pageContext.request.contextPath}/login.htm" method='POST'>
                        <table>
                            <h1>Admin login</h1>
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid username and password.</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
                            <tr>
                                <td><spring:message key="label.username"/>:</td>
                                <td><input type='text' name='username' value=''></td>
                            </tr>
                            <tr>
                                <td><spring:message key="label.password"/>:</td>
                                <td><input type='password' name='password' /></td>
                            </tr>
                            <tr>
                                <td><input class="btn btn-primary" name="submit" type="submit" value="<spring:message key="label.submit"/>" /></td>
                            </tr>
                        </table>
                    </form>
                    <a class="btn btn-primary" href="/index.htm">Go back to home page</a>
                </div>
            </div>
        </div>
    </div>
</head>
<body>

</body>
</html>
