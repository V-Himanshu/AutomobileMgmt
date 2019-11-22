<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Automobile Management System</title>
<script type="text/javascript">
function showTime(){
	var today = new Date();
	var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate()   + today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
	alert(date);
}

</script>
</head>
<body>
	<form:form action="processAttendance" modelAttribute="attendance">
		Employee ID: <form:input type="text" path="username" />		
		<br><br>
		Password: <form:input type="password" path="password" />
		<br><br>
		<input type="submit" value="Submit" onclick="showTime()"/>	
	</form:form>
	
	${displayTime }
</body>
</html>

