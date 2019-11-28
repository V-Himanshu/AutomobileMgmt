<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Automobile Management System</title>
</head>
<body>
	<%
		response.setHeader("Cache-control", "no-cache, no-store, must validate");
	%>
	<%
		response.setHeader("Pragma", "no-cache");
	%>
	<%
		response.setDateHeader("Expires", 0);
	%>
	<button onclick="window.location.href='add_employee';">Add
		Employee</button>
	<button onclick="window.location.href='update_employee';">Update
		Employee</button>
	<button onclick="window.location.href='view_employee';">View
		Employee</button>
	<button onclick="window.location.href='admin_view_attendance';">Attendance</button>
	
	
	<a href="logout">Logout</a>
</body>
</html>