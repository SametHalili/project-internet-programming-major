<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="pageTitle" value="Index - Blabla Forum"></jsp:param>
</jsp:include>

<body>
<jsp:include page="header.jsp"></jsp:include>
<h1>Profile: <strong>${user.username}</strong></h1>
<p>This is your profile! Only you can access this page at the moment.</p>
<p>This is WIP.</p>
<a href="/index.htm">Go back</a>
</body>
</html>