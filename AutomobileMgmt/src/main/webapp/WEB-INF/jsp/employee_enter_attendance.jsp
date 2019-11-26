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
		<input type="submit" value="Submit"/>
	</form:form>



</body>
</html>

