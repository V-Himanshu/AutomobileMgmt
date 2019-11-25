package com.automobile.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.automobile.model.Attendance;
import com.automobile.model.Login;

public interface AdminDao {

	boolean checkCredentials(Login login);

	List<Attendance> checkAttendance(Login login, LocalDate date);

	void updateInDetails(Login login, LocalDate localDate, LocalTime localTime);

	void updateOutDetails(Login login, LocalDate localDate, LocalTime localTime, String workedHours);

}
