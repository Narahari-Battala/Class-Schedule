<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</script>
<title>Insert title here</title>
</head>
<%@ include file="Header.jsp" %>
<body>
    <div class="general">
<div align="center">
<form  id="form1" name="LoginForm"  method="post" action="AdminServlet" >
 <input type="hidden" name="action"   value="validatecredentials" />
<p>Already Registered: SIGN IN</p>
<table>
	<tr><td>Enter ID:</td><td><input type="text" name="id"></td></tr>  
	<tr><td>Enter Password:</td><td><input type="password" name="password"></td></tr>
	<tr></tr>
	</table>
	<c:if test="${loginuser == 'admin'}" >
<input type="submit" name="submit" value="Login">
</c:if>
</form>

<form id="form2" name="RegistrationForm"  method="post" action="register.jsp" >
     
    <input type="hidden" name="submit"   value="register" />
    
<c:if test="${loginuser == 'student'}" >
<p>New Student?? please register here</p>
</c:if>
</form>

<form  method="post" action="adminregister.jsp">
<c:if test="${loginuser == 'admin'}" >
<p>New Admin?? please register here</p>
</c:if>
<input type="submit" name ="submit" value="register">
<br>
<div class="validate">
	<c:if test="${not empty invalidcredentials}">
    	${invalidcredentials} <br> <br>
	</c:if>
	</div>
</form>
</div>
        
    </div>
</body>
</html>