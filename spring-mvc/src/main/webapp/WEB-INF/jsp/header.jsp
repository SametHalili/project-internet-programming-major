<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="jumbotron text-center">
    <h1 class="display-1">Omnia</h1>
    <h2 class="lead">Discuss everything!</h2>
    <div class="dropdown">
        <button class="btn btn-danger dropdown-toggle" type="button" id="dropdownMenuButton1"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><spring:message key="app.lang"/></button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <a class="dropdown-item" href="?lang=en"><spring:message key="app.lang.english"/></a>
            <a class="dropdown-item" href="?lang=fr"><spring:message key="app.lang.french"/></a>
            <a class="dropdown-item" href="?lang=nl"><spring:message key="app.lang.dutch"/></a>
        </div>
    </div>
</div>