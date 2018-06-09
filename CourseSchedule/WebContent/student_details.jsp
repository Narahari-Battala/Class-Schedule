<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheets" type="text/css" href="styles/style.css">
<title>Add/Drop Courses/View Schedule</title>
</head>
<%@ include file="Header.jsp" %>
<body>
<form action="StudentServlet" method="get"  onsubmit="return validateEntry();">
<div class="details">
<div class="container1" >
    <a href="termdept.jsp"><img src="images/add_course.png" width="200" height="60" alt="" />Add Course</a><br>
    </div>
    
    <div class="container2">
    <a href="dropschedulemain.jsp"><img src="images/dean.jpg" width="200" height="60" alt="" />Drop Course</a><br>
   </div>
   
   <div class="container3">
    <a href="http://localhost:8080/CourseSchedule/StudentServlet?action=view"><img src="images/schedule.jpg" width="200" height="60" alt="" />View Schedule</a><br>
   </div>
</div>
</form>
</body>
</html>