 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="Header.jsp" %>  
<title>Home|UNC Charlotte</title>
<script type="text/javascript">
        function noBack()
         {
             window.history.forward()
         }
        noBack();
        window.onload = noBack;
        window.onpageshow = function(evt) { if (evt.persisted) noBack() }
        window.onunload = function() { void (0) }
    </script>
</head>
<body>

<form action="StudentServlet" method="get"  onsubmit="return validateEntry();">
<div class="container2">
<img src="images/students.jpg" width="200px" height="150 px">
Students <a href="http://localhost:8080/CourseSchedule/StudentServlet?action=student">Click Here</a>
</div>
<div class="container3">
<img src="images/dean.jpg" width=" 200px" height="150 px">
Admins <a href="http://localhost:8080/CourseSchedule/StudentServlet?action=admin">Click Here</a>
</div>
</form>
</body>
</html>