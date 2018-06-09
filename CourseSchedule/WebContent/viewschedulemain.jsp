<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Term</title>
</head>
<%@ include file="Header.jsp" %>
<body>
<form action ="StudentServlet" method="post">
<div class="container2">
<label> Select the Term: </label>
<select id="selecterm" name ="term">
  <option selected value="base">Please Select</option>
   <option value="S2018">Fall 2018</option>
  <option value="SS18">Second Summer 2018</option>
  <option value="FS2018">First Summer 2018</option>
</select>
</div>
<div class="submit">
<input type="submit" name="submit" value="View Schedule">
</div>
</form>
</body>
</html>