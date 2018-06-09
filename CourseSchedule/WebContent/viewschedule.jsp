<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheets" type="text/css" href="styles/mystyle.css" >
<title>Current Schedule</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="Header.jsp" %>
<body>
<div class="container">
<h4>Current Schedule</h4>

<c:if test="${not empty errormsg}">
    <c:out value="${ errormsg}"/>
</c:if>
<c:if test="${empty errormsg}">
<table>
<c:if test="${not empty section}">
<tr> 
<th>SNum</th>
<th>Subj</th>
<th>Title</th>
<th>Days</th>
<th>Start Time</th>
<th>End Time</th>
<th>Instructor</th>
<th>Location</th>
<th>Room</th>
<th></th>
</tr>
</c:if>
<c:forEach items="${section}" var="entry">
<br>
<tr>
    <td style="text-align:center">${entry.getSectionNum()}</td>
     <td style="text-align:center">${entry.getDept_id()}</td>
     <td style="text-align:center">${entry.getCourseName()}</td>
     <td style="text-align:center">${entry.getDay()}</td>
     <td style="text-align:center">${entry.getStarttime()}</td>
     <td style="text-align:center">${entry.getEndTime()}</td>
     <td style="text-align:center">${entry.getInstructor()}</td>
     <td style="text-align:center">${entry.getBuildingName()}</td>
     <td style="text-align:center">${entry.getRoomId()}</td>
     </tr>
</c:forEach>
</table>
</c:if>
</div>
</body>
</html>