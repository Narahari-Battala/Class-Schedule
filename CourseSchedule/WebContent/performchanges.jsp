<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheets" type="text/css" href="styles/style.css">
<title>Select Action</title>
</head>
<%@ include file="Header.jsp" %>
<body>
<% String action = request.getParameter("submit");%>
<% if(action.equals("Make Changes to Courses")){%>
<c:if test="${not empty errormsg}">
${errormsg} <br> <br>
</c:if>

<div class="details">
<div class="container1" >
<form action="AdminServlet" method="post">
    <input type="submit" name ="submit" value ="Add Course">
    </form>
    </div>
    
    <div class="container1" >
<form action="AdminServlet" method="post">
    <input type="submit" name ="submit" value ="View Courses">
    </form>
    </div>
   
   <div class="container3">
    <form action="AdminServlet" method="post">
    <input type="submit" name ="submit" value ="Delete Course">
    </form>
   </div>
</div>
<%} %>
<% if(action.equals("Make Changes to Departments")){%>
<div class="details">
<div class="container1" >
<form action="AdminServlet">
    <input type="submit" name ="submit" value ="Add Department">
    </form>
    </div>
    
    <div class="container2">
    <form action="AdminServlet">
    <input type="submit" name ="submit" value ="View Departments">
    </form>

   </div>
   
   <div class="container3">
    <form action="AdminServlet">
    <input type="submit" name ="submit" value ="Delete Department">
    </form>
   </div>
</div>
<%} %>
<% if(action.equals("Make Changes to Sections")){%>
<div class="details">
<div class="container1" >
<form action="AdminServlet">
    <input type="submit" name ="submit" value ="Add Section">
    </form>
    </div>
    
    <div class="container2">
    <form action="AdminServlet" method="post">
    <input type="submit" name ="submit" value ="View Sections">
    </form>

   </div>
   
   <div class="container3">
    <form action="AdminServlet">
    <input type="submit" name ="submit" value ="Delete Section">
    </form>
   </div>
</div>
<%} %>
<% if(action.equals("Make Changes to Students list")){%>
<div class="details">
<div class="container1" >
<form action="AdminServlet">
    <input type="submit" name ="submit" value ="Add Student">
    </form>
    </div>
    
    <div class="container2">
    <form action="AdminServlet">
    <input type="submit" name ="submit" value ="View Students">
    </form>

   </div>
   
   <div class="container3">
    <form action="AdminServlet">
    <input type="submit" name ="submit" value ="Delete Student">
    </form>
   </div>
</div>
<%} %>
</body>
</html>