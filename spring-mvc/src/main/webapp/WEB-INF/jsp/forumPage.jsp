<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="pageTitle" value="Index - Blabla Forum"></jsp:param>
</jsp:include>

<body>
<div class="jumbotron text-center">
    <h1 class="display-1">Blabla Forum</h1>
    <h2>Welcome to the forum!</h2>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <h2>Forums</h2>
            <p>Chose a forum</p>
            <div class="card">
                <div class="card-header">List of Threads</div>
                <div class="card-body"><a href="#">Thread 1</a></div>
                <div class="card-body"><a href="#">Thread 2</a></div>
                <div class="card-body"><a href="#">Thread 3</a></div>
                <div class="card-body"><a href="#">Thread 4</a></div>
                <div class="card-body"><a href="#">Thread 5</a></div>
            </div>
        </div>
        <a href="#" class="btn btn-primary btn-lg" role="button">Create new thread</a>
        <div class="col-sm-2"></div>
    </div>
</div>
</body>
</html>
