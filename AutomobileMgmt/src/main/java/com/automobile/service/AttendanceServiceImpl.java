
package com.automobile.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.automobile.dao.AttendanceDao;
import com.automobile.model.Attendance;
import com.automobile.model.Employee;
import com.automobile.model.Login;
import com.automobile.model.SearchAttendance;

public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceDao attendanceDao;

	/**
	 * Check if user-name and password are valid for logging attendance.
	 */
	public boolean checkCredentials(Login login) {
		return attendanceDao.checkCredentials(login);
	}

	/**
	 * Add attendance In-time and Out-time for specific employee in database.
	 */
	public String addAttendance(Login login) {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		LocalDateTime fromDateTime = LocalDateTime.of(localDate, localTime);
		String output;

		List<Attendance> list = attendanceDao.checkAttendance(login, localDate);
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

			attendanceDao.updateOutDetails(login, localDate, localTime, workedHours, list.get(0).getInDate());

			output = "Out-Date: " + localDate + "\n" + "Out-Time: " + localTime;
			return output;
		}
		/**
		 * If no current date record for employee found, create a new row updating
		 * In-date and In-time of employee and setting active_status to 'y'.
		 */
		attendanceDao.updateInDetails(login, localDate, localTime);

		output = "In-Date: " + localDate + "\n" + "In-Time: " + localTime;
		return output;
	}

	/**
	 * Admin searching employee for attendance.
	 */
	public List<Employee> searchEmployee(Employee employee) {
		if(employee.getEmployeeId().equals(null) || (employee.getEmployeeId().equals(""))) {
			return attendanceDao.searchEmployeeByName(employee);
		}else if(employee.getEmployeeName().equals(null) || employee.getEmployeeName().equals("")) {
			return attendanceDao.searchEmployeeById(employee);
		}
		return attendanceDao.searchEmployee(employee);
	}

	/**
	 * Admin gets list of all attendance details based on employee id, from and to
	 * date.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List searchAttendance(SearchAttendance searchAttendance) {
		List<Attendance> list = attendanceDao.searchAttendance(searchAttendance);
		List<Attendance> list2 = new ArrayList<Attendance>();
		LocalDate from = LocalDate.parse(searchAttendance.getFromDate());
		LocalDate to = LocalDate.parse(searchAttendance.getToDate());

		int i = 0;
		int listSize = list.size();
		long size = to.getDayOfYear() - from.getDayOfYear();

		System.out.println(size);

		Period p2 = Period.between(to, LocalDate.now());
		int count = p2.getDays();

		if (count < 0) {
			to = LocalDate.now();
			size = to.getDayOfYear() - from.getDayOfYear();
		}

		System.out.println(size);
		int workingDaysInAMonth = 0;

		while (size != 0) {
			Attendance absentDays = new Attendance(searchAttendance.getEmployeeId(), "" + from, "NA", "NA", "NA", "NA",
					"Absent");
			if ((from.getDayOfWeek()).equals(DayOfWeek.SATURDAY) || (from.getDayOfWeek()).equals(DayOfWeek.SUNDAY)) {
				size--;
				from = from.plusDays(1);
				continue;
			}
			if ((listSize > 0) && ("" + from).equals(list.get(i).getInDate())) {

				list.get(i).setActiveStatus("Present");
				list2.add(list.get(i));
				i++;
				listSize--;
			} else {

				list2.add(absentDays);
			}
			workingDaysInAMonth++;
			from = from.plusDays(1);
			size--;
		}

		String leaves = "" + (workingDaysInAMonth - list.size());
		String present = "" + list.size();
		String total = "" + workingDaysInAMonth;

		List<String> totalDays = new ArrayList<String>();
		totalDays.add(total);
		totalDays.add(present);
		totalDays.add(leaves);

		List finalList = new ArrayList();
		finalList.add(list2);
		finalList.add(totalDays);

		return finalList;
	}

	/**
	 * Employee gets list of all attendance details based on from and to date.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List employeeSearchAttendance(SearchAttendance searchAttendance, String employeeId) {
		List<Attendance> list = attendanceDao.employeeSearchAttendance(searchAttendance, employeeId);
		List<Attendance> list2 = new ArrayList<Attendance>();
		LocalDate from = LocalDate.parse(searchAttendance.getFromDate());
		LocalDate to = LocalDate.parse(searchAttendance.getToDate());

		int i = 0;
		int listSize = list.size();
		long size = to.getDayOfYear() - from.getDayOfYear();

		System.out.println(size);

		Period p2 = Period.between(to, LocalDate.now());
		int count = p2.getDays();

		if (count < 0) {
			to = LocalDate.now();
			size = to.getDayOfYear() - from.getDayOfYear();
		}

		System.out.println(size);
		int workingDaysInAMonth = 0;

		while (size != 0) {
			Attendance absentDays = new Attendance(searchAttendance.getEmployeeId(), "" + from, "NA", "NA", "NA", "NA",
					"Absent");
			if ((from.getDayOfWeek()).equals(DayOfWeek.SATURDAY) || (from.getDayOfWeek()).equals(DayOfWeek.SUNDAY)) {
				size--;
				from = from.plusDays(1);
				continue;
			}
			if ((listSize > 0) && ("" + from).equals(list.get(i).getInDate())) {

				list.get(i).setActiveStatus("Present");
				list2.add(list.get(i));
				i++;
				listSize--;
			} else {

				list2.add(absentDays);
			}
			workingDaysInAMonth++;
			from = from.plusDays(1);
			size--;
		}

		String leaves = "" + (workingDaysInAMonth - list.size());
		String present = "" + list.size();
		String total = "" + workingDaysInAMonth;

		List<String> totalDays = new ArrayList<String>();
		totalDays.add(total);
		totalDays.add(present);
		totalDays.add(leaves);

		List finalList = new ArrayList();
		finalList.add(list2);
		finalList.add(totalDays);

		return finalList;

	}
}
