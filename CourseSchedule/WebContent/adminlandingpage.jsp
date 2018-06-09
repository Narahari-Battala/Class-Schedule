<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheets" type="text/css" href="styles/mystyle.css" >
<title>Welcome Admin</title>
</head>
<%@ include file="Header.jsp" %>
<body>
<c:if test="${not empty errormsg}">
${errormsg} <br> <br>
</c:if>
<div class ="containeradmin">
<form action="performchanges.jsp">
<input type="submit" name="submit" value="Make Changes to Courses" >
</form>
</div>
<div class ="containeradmin">
<form action="performchanges.jsp">
<input type="submit" name="submit" value="Make Changes to Departments" >
</form>
</div>
<div class ="containeradmin">
<form action="performchanges.jsp">
<input type="submit" name="submit" value="Make Changes to Sections" >
</form>
</div>

<div class ="containeradmin">
<form action="performchanges.jsp">
<input type="submit" name="submit" value="Make Changes to Students list" >
</form>
</div>
</body>
</html>