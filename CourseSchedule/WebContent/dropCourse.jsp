<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheets" type="text/css" href="styles/mystyle.css" >
<title>Drop Course</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="Header.jsp" %>
<body>
<form action="StudentServlet" method="post"  onsubmit="return validateEntry();">
<div class="container">
<c:if test="${not empty errormsg}">
    <c:out value="${ errormsg}"/>
</c:if>
<c:if test="${empty errormsg}">
<table>
<c:if test="${not empty studentsectionlist}">
<tr> 
<th></th>
<th>SNum</th>
<th>Subj </th>
<th>Title</th>
<th></th>
</tr>
</c:if>
<c:forEach items="${studentsectionlist}" var="entry">
<br>
<tr>
     <td ><input type="radio" name="section" value="${entry.value.getSectionId()}" required></td>
     <td style="text-align:center">${entry.value.getSectionNum()}</td>
     <td style="text-align:center">${entry.value.getDept_id()}</td>
     <td style="text-align:center">${entry.value.getCourseName()}</td>
     <td><input type="submit" name="submit" value="Drop" /></td>
     </tr>
</c:forEach>
</table>
</c:if>
<div>
<c:if test="${not empty resultmessage}">
    <c:out value="${ resultmessage}"/>
</c:if>

</div>
</div>
</form>
</body>
</html>