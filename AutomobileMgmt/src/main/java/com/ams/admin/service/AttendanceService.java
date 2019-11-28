package com.ams.admin.service;

import java.util.List;

import com.ams.admin.model.EmployeeAttendance;
import com.ams.admin.model.Login;
import com.ams.admin.model.SearchAttendance;

public interface AttendanceService {

	boolean checkCredentials(Login login);

	String addAttendance(Login login);

	List<EmployeeAttendance> searchEmployee(EmployeeAttendance employeeAttendance);

	@SuppressWarnings("rawtypes")
	List searchAttendance(SearchAttendance searchAttendance);

	@SuppressWarnings("rawtypes")
	List employeeSearchAttendance(SearchAttendance searchAttendance, String string);

	

}
