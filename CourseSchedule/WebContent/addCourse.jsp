<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheets" type="text/css" href="styles/mystyle.css" >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Course</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="Header.jsp" %>
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
<body>
<form action="StudentServlet" method="post"  onsubmit="return validateEntry();">

<div class="containeradd">
<c:if test="${not empty errormsg}">
    <c:out value="${ errormsg}"/>
</c:if>
<c:if test="${empty errormsg}">
<table>
<c:if test="${not empty sectionlist}">
<tr> 
<th></th>
<th>SNum</th>
<th>Subj</th>
<th>Title</th>
<th>Days</th>
<th>Start Time</th>
<th>End Time</th>
<th>Capacity</th>
<th>Instructor</th>
<th>Location</th>
<th>Room</th>
</tr>
</c:if>
<c:forEach items="${sectionlist}" var="entry">
<br>
<tr>
     <td ><input type="radio" name="section" value="${entry.value.getSectionId()}" required></td>
     <td style="text-align:center">${entry.value.getSectionNum()}</td>
     <td style="text-align:center">${entry.value.getDept_id()}</td>
     <td style="text-align:center">${entry.value.getCourseName()}</td>
     <td style="text-align:center">${entry.value.getDay()}</td>
     <td style="text-align:center">${entry.value.getStarttime()}</td>
     <td style="text-align:center">${entry.value.getEndTime()}</td>
     <td style="text-align:center">${entry.value.getSectionLimit()}</td>
     <td style="text-align:center">${entry.value.getInstructor()}</td>
     <td style="text-align:center">${entry.value.getBuildingName()}</td>
     <td style="text-align:center">${entry.value.getRoomId()}</td>
     <td><input type="submit" name="submit" value="Add" /></td>
     </tr>
</c:forEach>
</table>
</c:if>
<c:if test="${not empty resultmessage}">
    <c:out value="${ resultmessage}"/>
</c:if>
</div>
</form>
</body>
</html>