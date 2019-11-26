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

	<form:form action="searchEmployee" modelAttribute="searchEmployee">
Employee Id: <form:input path="employeeId" />
		<br>
		<input type="submit" value="Search" />
		<br>
		${message }
	</form:form>

	<table>
		<tr>
			<th>Employee Id</th>
			<th>Employee name</th>
			<th>Department</th>
			<th>View</th>
		</tr>
		<c:forEach var="employee" items="${employeeList}">
			<tr>
				<form:form action="displayAttendance" modelAttribute="employeeData">
					<td><input type="text" value="${employee.getEmployeeId() }" name="employeeId" readonly="readonly"/></td>
					<td><input type="text" value="${employee.getEmployeeName() }" name="employeeName" readonly="readonly"/></td>
					<td><input type="text" value="${employee.getDepartment() }" name="department" readonly="readonly"/></td>
					<td><input type="submit" value="View Attendance" /></td>
				</form:form>
			</tr>
		</c:forEach>
	</table>
</body>
</html>