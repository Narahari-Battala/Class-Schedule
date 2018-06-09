<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Term/Department</title>
</head>
<%@ include file="Header.jsp" %>
<body>
<form action ="StudentServlet" method="post" id="deptterm" name="DeptTerm">
<div class="container2">
<label> Select the Term: </label>
<select id="selecterm" name ="term" required>
  <option selected value="base">Please Select</option>
  <option value="S2018">Fall 2018</option>
  <option value="SS2018">Second Summer 2018</option>
  <option value="FS2018">First Summer 2018</option>
</select>
</div>
<div class="container3">
<label> Department: </label>
<select id="selectdept" name ="dept" required>
  <option selected value="base">Please Select</option>
  <option value="ITCS">Computer Science</option>
  <option value="MTM">Mathematics</option>
  <option value="CEST">Civil Engineering</option>
  <option value="ECET">Electrical Engineering</option>
</select>
</div>
<div class="submit">
<input type="submit" name="submit" value="Submit">
</div>
</form>
</body>
</html>