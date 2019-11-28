<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Automobile Management System</title>
<meta charset="utf-8">

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
	<form:form action="processAttendance" modelAttribute="attendance"
		method="post">
		Employee ID: <form:input type="text" path="username" />
		<br>
		<br>
		Password: <form:input type="password" path="password" />
		<br>
		<br>
		${message }
		<br>
		<input type="submit" value="Submit" />
	</form:form>
<button onclick="window.location.href='login';">Back</button>

	${displayTime }
	<a href="employee_enter_attendance">${close }</a>

</body>
</html>

