<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Automobile Management System</title>
</head>
<body>
	
	<form:form action="searchAttendance" modelAttribute="searchAttendance">
		<input type="text" value="${employee_id }" name="employeeId" readonly="readonly" />
		<form:input type="date" path="fromDate" />
		<form:input type="date" path="toDate" />
		<input type="submit" value="Search" />
	</form:form>
	
	<table>
	<tr>
	<th>Employee Id</th>
	<th>In Date</th>
	<th>In Time</th>
	<th>Out Date</th>
	<th>Out Time</th>
	<th>Worked Hours</th>
	</tr>
	<c:forEach var="attendance" items="${attendanceList }">
	<tr>
	<td>${attendance.getEmployeeId() }</td>
	<td>${attendance.getInDate() }</td>
	<td>${attendance.getInTime() }</td>
	<td>${attendance.getOutDate() }</td>
	<td>${attendance.getOutTime() }</td>
	<td>${attendance.getWorkedHours() }</td>
	</tr>
	
	</c:forEach>
	
	</table>
</body>
</html>