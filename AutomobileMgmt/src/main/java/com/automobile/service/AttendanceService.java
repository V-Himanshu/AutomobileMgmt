package com.automobile.service;

import java.util.List;

import com.automobile.model.Employee;
import com.automobile.model.Login;
import com.automobile.model.SearchAttendance;

public interface AttendanceService {

	boolean checkCredentials(Login login);

	String addAttendance(Login login);

	List<Employee> searchEmployee(Employee employee);

	@SuppressWarnings("rawtypes")
	List searchAttendance(SearchAttendance searchAttendance);

	@SuppressWarnings("rawtypes")
	List employeeSearchAttendance(SearchAttendance searchAttendance, String string);

	

}
