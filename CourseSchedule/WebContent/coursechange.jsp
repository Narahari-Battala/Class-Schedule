<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheets" type="text/css" href="styles/mystyle.css" >
<title>Add/Update/Delete Course</title>
</head>
<%@ include file="Header.jsp" %>
<body>
<form action="AdminServlet" method="post">
<% String action =(String) request.getAttribute("action");%>
<% if(action.equals("Add Course")){%>
<div class="general">
<table>
	<tr><td>Course ID:</td><td><input type="text" name="courseid" value="${cid}"></td></tr>  
	<tr><td>Course Title:</td><td><input type="text" name="coursetitle" value ="${cname}"></td></tr>
	<tr>
	</tr>
	</table>
	</div>
<div class="container3">
	<label>Select Department: </label>
	<select id="selectdept" name ="dept">
	<option selected value="base">Please Select</option>
	<c:forEach items="${departments}" var="entry">
  <option value ="${entry.getDepartmentId()}"> ${entry.getDepartmentName()} </option>
  </c:forEach>
  </select>
<%} %>
<% if(action.equals("Add Course")){%>
</div>
<div class="submit">
<input type="submit" name="submit" value="AddCourse">
<br><br>
<c:if test="${not empty successmsg}">
${successmsg} <br> <br>
<c:if test="${not empty errormsg}">
${errormsg} <br> <br>
</c:if>
</c:if>
</div>
<%} %>
<% if(action.equals("View Courses")){%>
<div class="general">
<table><c:if test="${not empty courses}">
<tr> 
<th>Course Number</th>
<th>Course Name </th>
</tr>
</c:if>
	<c:forEach items="${courses}" var="entry">
	<tr>
     <td style="text-align:center">${entry.getCourseNumber()}</td>
     <td style="text-align:center">${entry.getCourseName()}</td>
     </tr>
  </c:forEach>
	</table>
	</div>
<%} %>

<%if(action.equals("Delete Course")){%>
<div class="container3">
	<label>Select Course to be Deleted: </label>
	<table>
<c:if test="${not empty courses}">
<tr> 
<th></th>
<th>Course Number</th>
<th>Course Name </th>
</tr>
</c:if>
	<c:forEach items="${courses}" var="entry">
	<tr>
  <td ><input type="radio" name="course" value="${entry.getCourseId()}" required></td>
     <td style="text-align:center">${entry.getCourseNumber()}</td>
     <td style="text-align:center">${entry.getCourseName()}</td>
     </tr>
  </c:forEach>
  </table>
</div>
<%} %>
<%if(action.equals("Delete Course")){%>
<div class="submit">
<input type="submit" name="submit" value="DeleteCourse">
<br><br>
<c:if test="${not empty successmsg}">
${successmsg} <br> <br>
<c:if test="${not empty errormsg}">
${errormsg} <br> <br>
</c:if>
</c:if>
</div>
<%} %>
<% if(action.equals("Add Department")){%>
<div class="general">
<table>
	<tr><td>Department ID:</td><td><input type="text" name="departmentid" value="${did}"></td></tr>  
	<tr><td>Department Title:</td><td><input type="text" name="departmenttitle" value ="${dname}"></td></tr>
	<tr>
	</tr>
	</table>
	</div>
<%} %>
<%if(action.equals("Add Department")){%>
<div class="submit">
<input type="submit" name="submit" value="AddDepartment">
<br><br>
<c:if test="${not empty successmsg}">
${successmsg} <br> <br>
<c:if test="${not empty errormsg}">
${errormsg} <br> <br>
</c:if>
</c:if>
</div>
<%} %>
<% if(action.equals("View Departments")){%>
<div class="general">
<table>
	<tr>List of Departments:</tr>  
	<tr>
<th>Department Id</th>
<th>Department Name </th>
</tr>
	<c:forEach items="${departments}" var="entry">
	<tr>
	<td>${entry.getDepartmentId()}</td>
	<td>${entry.getDepartmentName()}</td>
	</tr>
	</c:forEach>
	<tr>
	</tr>
	</table>
	</div>
<%} %>

<%if(action.equals("Delete Department")){%>
<div class="container3">
	<label>Select Department to be Deleted: </label>
	<table>
<c:if test="${not empty departments}">
<tr> 
<th></th>
<th>Department Id:</th>
<th>Department Name </th>
</tr>
</c:if>
	<c:forEach items="${departments}" var="entry">
	<tr>
  <td ><input type="radio" name="department" value="${entry.getDepartmentId()}" required></td>
     <td style="text-align:center">${entry.getDepartmentId()}</td>
     <td style="text-align:center">${entry.getDepartmentName()}</td>
     </tr>
  </c:forEach>
  </table>
</div>
<%} %>

<%if(action.equals("Delete Department")){%>
<div class="submit">
<input type="submit" name="submit" value="DeleteDepartment">
<br><br>
<c:if test="${not empty successmsg}">
${successmsg} <br> <br>
<c:if test="${not empty errormsg}">
${errormsg} <br> <br>
</c:if>
</c:if>
</div>
<%} %>
<% if(action.equals("Add Section")){%>
<div class="general">
<table>
	<tr><td>Section Number:</td><td><input type="text" name="number" value="${snumber}"></td></tr>  
	<tr><td>SectionLimit:</td><td><input type="text" name="limit" value ="${slimit}"></td></tr>
	<tr>
	</tr>
	</table>
	</div>
<div class="container3">
	<label>Select Course: </label>
	<select id="selectcourse" name ="course">
	<option selected value="base">Please Select</option>
	<c:forEach items="${courses}" var="entry">
  <option value ="${entry.getCourseId()}"> ${entry.getCourseName()} </option>
  </c:forEach>
  </select>
  </div>
  <div class="container3">
	<label>Select Semester: </label>
	<select id="selectsemester" name ="semester">
	<option selected value="base">Please Select</option>
	<c:forEach items="${semesters}" var="entry">
  <option value ="${entry.getSemesterId()}"> ${entry.getSemseter()} </option>
  </c:forEach>
  </select>
  </div>
  <div class="container3">
	<label>Select Room: </label>
	<select id="selectroom" name ="room">
	<option selected value="base">Please Select</option>
	<c:forEach items="${rooms}" var="entry">
  <option value ="${entry.getRoomId()}"> ${entry.getRoomNum()} </option>
  </c:forEach>
  </select>
<%} %>
<% if(action.equals("Add Section")){%>
</div>
<div class="submit">
<input type="submit" name="submit" value="AddSection">
<br><br>
<c:if test="${not empty successmsg}">
${successmsg} <br> <br>
<c:if test="${not empty errormsg}">
${errormsg} <br> <br>
</c:if>
</c:if>
</div>
<%} %>
<%if(action.equals("view sections")){%>
<div class="container">
	<label>Current Sections </label>
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
<%} %>
<%if(action.equals("delete section")){%>
<div class="container">
	<label>Select section to be deleted:</label>
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
<tr>
    <td ><input type="radio" name="section" value="${entry.getSectionId()}" required></td>
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
<%} %>
<% if(action.equals("delete section")){%>
</div>
<div class="submit">
<input type="submit" name="submit" value="DeleteSection">
<br><br>
<c:if test="${not empty successmsg}">
${successmsg} <br> <br>
<c:if test="${not empty errormsg}">
${errormsg} <br> <br>
</c:if>
</c:if>
</div>
<%} %>
<% if(action.equals("add student")){%>
<div class="general">
<table>
	<tr><td>Student ID:</td><td><input type="text" name="studentid" value="${sid}"></td></tr>  
	<tr>
	</tr>
	</table>
	</div>
<div class="container3">
	<label>Select Department: </label>
	<select id="selectdept" name ="dept">
	<option selected value="base">Please Select</option>
	<c:forEach items="${departments}" var="entry">
  <option value ="${entry.getDepartmentId()}"> ${entry.getDepartmentName()} </option>
  </c:forEach>
  </select>
<%} %>
<% if(action.equals("add student")){%>
</div>
<div class="submit">
<input type="submit" name="submit" value="AddStudent">
<br><br>
<c:if test="${not empty successmsg}">
${successmsg} <br> <br>
<c:if test="${not empty errormsg}">
${errormsg} <br> <br>
</c:if>
</c:if>
</div>
<%} %>


<% if(action.equals("View Students")){%>
<div class="general">
<table>
	<tr>List of Students:</tr>  
	<tr>
<th>Student Id</th>
<th>Department </th>
</tr>
	<c:forEach items="${students}" var="entry">
	<tr>
	<td>${entry.getId()}</td>
	<td>${entry.getDepartment()}</td>
	</tr>
	</c:forEach>
	<tr>
	</tr>
	</table>
	</div>
<%} %>
<%if(action.equals("Delete Student")){%>
<div class="container3">
	<label>Select Student to be Deleted: </label>
	<table>
<c:if test="${not empty students}">
<tr> 
<th></th>
<th>Student Id:</th>
<th>Department</th>
</tr>
</c:if>
	<c:forEach items="${students}" var="entry">
	<tr>
  <td ><input type="radio" name="student" value="${entry.getId()}" required></td>
     <td style="text-align:center">${entry.getId()}</td>
     <td style="text-align:center">${entry.getDepartment()}</td>
     </tr>
  </c:forEach>
  </table>
  
</div>
<%} %>

<%if(action.equals("Delete Student")){%>
<div class="submit">
<input type="submit" name="submit" value="DeleteStudent">
<br><br>
<c:if test="${not empty successmsg}">
${successmsg} <br> <br>
</c:if>
</div>
<%} %>

</form>
</body>
</html>