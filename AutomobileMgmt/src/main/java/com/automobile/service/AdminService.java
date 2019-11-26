package com.automobile.service;

import java.util.List;

import com.automobile.model.Attendance;
import com.automobile.model.Employee;
import com.automobile.model.Login;
import com.automobile.model.SearchAttendance;

public interface AdminService {

	boolean checkCredentials(Login login);

	String addAttendance(Login login);

	List<Employee> searchEmployee(Employee employee);

	List<Attendance> searchAttendance(SearchAttendance searchAttendance);

	

}
