<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<%@ include file="Header.jsp" %>
<body>

    <div id="reg">
 <h2 class="centre" align="center">Registration Form</h2><br>
  <marquee behavior="scroll" direction="right">** All Fields are Mandatory **</marquee>
    <form action="StudentServlet" method="post"  onsubmit="return validateEntry();">
        <input type="hidden" name="action"  value="display" />
<table width="600px" align="center"  >
 
      <!-- table to display-->

<tr>
        <td  width="200">Campus Id.</td>
        <td width="200">
          <input name="id" type="text" id="campusid" value ="${id}" size="30" maxlength="50" required/>
        </td>
</tr>

<tr>
        <td  width="200">First Name</td>
        <td width="200">
          <input name="firstname" type="text" id="fname" value ="${firstname}" size="30" required/>
        </td>
</tr>

<tr>
        <td  width="200">Last Name</td>
        <td width="200">
          <input name="lastname" type="text" id="lname" value ="${lastname}" size="30" required/>
        </td>
</tr>

<tr>
        <td  width="200">Email Id</td>
        <td width="200">
          <input name="email" type="email" id="email" value ="${email}" size="30" onblur="validateEmail();" required/>
        </td>
        <td><p id="emailError">${emailexists}</p></td>
</tr>

    <tr>
        <td  width="200">Password</td>
        <td width="200">
          <input name="password" type="password" id="pwd" size="30" onblur="passwordValidate();" required/>
        </td>
        <td><p id="p"></p></td>
</tr>

 <tr>
        <td  width="200">Confirm Password</td>
        <td width="200">
          <input name="cpassword" type="password" id="cpwd" size="30" onblur="passwordCheck();" required/>
        </td>
        <td width="600"><p id="c"></p></td>
</tr>

</table><br>
   
<div  align="center">
 <input type="submit" name="submit" value="Register"  />
    <!-- button for save-->
 
</div>

<div class="validate">
	<c:if test="${not empty errormsg}">
    	${errormsg} <br> <br>
	</c:if>
	</div>

</form>
    <div class="centr" id="ud"> </div>
    </div>
</body></html>