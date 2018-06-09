<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href ="styles/style.css">
<link rel="stylesheet" type="text/css" href ="styles/mystyle.css">
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
<form action="StudentServlet" method="post"  onsubmit="return validateEntry();">
    <div id="header" >
    <h1> University Of North Carolina at Charlotte</h1>
</div>
	<div id="menu-wrapper">
		<div id="menu" >
			<ul>
				<li class="home"><a href="index.jsp">Home</a></li>
				
				<li class="about unc"><a href="about-unc.jsp">About UNC Charlotte</a></li>
				
				<li class="dashboard"> 
				<c:if test="${empty usercontrol}">
    <a href="index.jsp">My DashBoard</a>
</c:if>
<c:if test="${usercontrol == 'student'}" >
<a href="student_details.jsp">My DashBoard</a>
</c:if>
<c:if test="${usercontrol == 'admin'}" >

<a href="adminlandingpage.jsp">My DashBoard</a>
</c:if>
</li>  
<li class="logout"> 
				<c:if test="${empty usercontrol}">
</c:if>
<c:if test="${usercontrol == 'student'}" >
<a href="http://localhost:8080/CourseSchedule/StudentServlet?action=logout">LogOut</a>
</c:if>
<c:if test="${usercontrol == 'admin'}" >

<a href="http://localhost:8080/CourseSchedule/StudentServlet?action=logout">LogOut</a>
</c:if>
</li>             
           
			</ul>
		</div>
	</div>
	</form>
</body>
</html>