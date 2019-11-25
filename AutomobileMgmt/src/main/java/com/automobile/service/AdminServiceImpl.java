package com.automobile.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.automobile.dao.AdminDao;
import com.automobile.model.Attendance;
import com.automobile.model.Login;

public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;

	/**
	 * Check if user-name and password are valid for logging attendance.
	 */
	public boolean checkCredentials(Login login) {
		return adminDao.checkCredentials(login);
	}

	/**
	 * Add attendance In-time and Out-time for specific employee in database.
	 */
	public String addAttendance(Login login) {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		LocalDateTime fromDateTime = LocalDateTime.of(localDate, localTime);
		String output;

		List<Attendance> list = adminDao.checkAttendance(login, localDate);
		/**
		 * Check if employee exist for particular date. If present, also check if active
		 * status is 'y' or In-date is same. In both the cases only Out-time should be
		 * updated.
		 */
		if (list.size() > 0
				&& ((list.get(0).getActiveStatus()).equals("y") || (list.get(0).getInDate()).equals("" + localDate))) {
			/**
			 * Calculating the difference between In-time and Out-time and updating the
			 * workedHours in database. workedHours is formatted in required format(hh:mm).
			 */
			LocalDate toDate = LocalDate.parse(list.get(0).getInDate());
			LocalTime toTime = LocalTime.parse(list.get(0).getInTime());
			LocalDateTime toDateTime = LocalDateTime.of(toDate, toTime);
			Duration d = Duration.between(fromDateTime, toDateTime);
			long absSeconds = Math.abs(d.getSeconds());
			String workedHours = String.format("%d:%d", absSeconds / 3600, (absSeconds % 3600) / 60);

			adminDao.updateOutDetails(login, localDate, localTime, workedHours);

			output = "Out-Date: " + localDate + "\n" + "Out-Time: " + localTime;
			return output;
		}
		/**
		 * If no current date record for employee found, create a new row updating
		 * In-date and In-time of employee and setting active_status to 'y'.
		 */
		adminDao.updateInDetails(login, localDate, localTime);

		output = "In-Date: " + localDate + "\n" + "In-Time: " + localTime;
		return output;
	}

}
