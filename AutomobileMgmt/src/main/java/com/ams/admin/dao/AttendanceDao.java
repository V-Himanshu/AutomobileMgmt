package com.ams.admin.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.ams.admin.model.Attendance;
import com.ams.admin.model.EmployeeAttendance;
import com.ams.admin.model.Login;
import com.ams.admin.model.SearchAttendance;

public interface AttendanceDao {

	boolean checkCredentials(Login login);

	List<Attendance> checkAttendance(Login login, LocalDate date);

	void updateInDetails(Login login, LocalDate localDate, LocalTime localTime);

	void updateOutDetails(Login login, LocalDate localDate, LocalTime localTime, String workedHours, String inDate);

	List<EmployeeAttendance> searchEmployee(EmployeeAttendance employeeAttendance);

	List<Attendance> searchAttendance(SearchAttendance searchAttendance);

	List<Attendance> employeeSearchAttendance(SearchAttendance searchAttendance, String employeeId);

	List<EmployeeAttendance> searchEmployeeByName(EmployeeAttendance employeeAttendance);

	List<EmployeeAttendance> searchEmployeeById(EmployeeAttendance employeeAttendance);

}
