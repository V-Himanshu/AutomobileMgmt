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

	<%
		response.setHeader("Cache-control", "no-cache, no-store, must validate");
	%>
	<%
		response.setHeader("Pragma", "no-cache");
	%>
	<%
		response.setDateHeader("Expires", 0);
	%>
	<form:form action="employeeSearchAttendance"
		modelAttribute="searchAttendance">
		<form:input type="date" path="fromDate" />
		<form:input type="date" path="toDate" />
		<input type="submit" value="Search" />
	</form:form>
<button onclick="window.location.href='home';">Back</button>

	<table>
		<tr>

			<th>In Date</th>
			<th>In Time</th>
			<th>Out Date</th>
			<th>Out Time</th>
			<th>Worked Hours</th>
			<th>Status</th>
		</tr>
		<c:forEach var="attendance" items="${attendanceList }">
			<tr>

				<td>${attendance.getInDate() }</td>
				<td>${attendance.getInTime() }</td>
				<td>${attendance.getOutDate() }</td>
				<td>${attendance.getOutTime() }</td>
				<td>${attendance.getWorkedHours() }</td>
				<td>${attendance.getActiveStatus() }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>

	<table>
		<tr>
			<td>Total Working Days:</td>
			<td>${totalDaysList.get(0) }</td>
		</tr>
		<tr>
			<td>Present Days:</td>
			<td>${totalDaysList.get(1) }</td>
		</tr>
		<tr>
			<td>Absent Days:</td>
			<td>${totalDaysList.get(2) }</td>
		</tr>

	</table>
</body>
</html>