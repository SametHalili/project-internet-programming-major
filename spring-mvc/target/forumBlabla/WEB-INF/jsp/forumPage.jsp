<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <h2>Forums</h2>
                <p>Chose a forum</p>
                <div class="card">
                    <div class="card-header">List of Threads</div>
                    <div class="list-group">
                        <div class="list-group-item"><a href="/thread.htm">Thread 1</a></div>
                        <div class="list-group-item"><a href="#">Thread 2</a></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-2"></div>
        <a href="#" class="btn btn-primary btn-lg" role="button">Create new thread</a>
    </div>
</body>
</html>
