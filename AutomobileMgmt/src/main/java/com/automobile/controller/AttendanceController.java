package com.automobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.automobile.model.Employee;
import com.automobile.model.Login;
import com.automobile.model.SearchAttendance;
import com.automobile.service.AttendanceService;

@Controller
public class AttendanceController {

	@Autowired
	AttendanceService attendanceService;

	/**
	 * Returns employee_enter_attendance.jsp page.
	 */
	@RequestMapping("/employee_enter_attendance")
	public String employeeEnterAttendance(Model model) {
		Login login = new Login();
		model.addAttribute("attendance", login);
		return "employee_enter_attendance";
	}

	/**
	 * Checks login credentials of employee and updates the attendance values for
	 * intended employees.
	 */
	@RequestMapping(value = "/processAttendance", method = RequestMethod.POST)
	public ModelAndView processAttendance(Login login) {
		if (attendanceService.checkCredentials(login)) {
			String time = attendanceService.addAttendance(login);
			ModelAndView mav = new ModelAndView("employee_enter_attendance");
			mav.addObject("attendance", new Login());
			mav.addObject("displayTime", time);
			mav.addObject("close", "Close");
			return mav;
		}
		ModelAndView mav = new ModelAndView("employee_enter_attendance");
		Login newLogin = new Login();
		mav.addObject("attendance", newLogin);
		mav.addObject("message", "Invalid Credentials");
		return mav;
	}

	/**
	 * Admin gets redirected to viewing and searching attendance page.
	 */
	@RequestMapping("/admin_view_attendance")
	public String adminViewAttendance(Model model) {
		Employee employee = new Employee();
		model.addAttribute("searchEmployee", employee);
		model.addAttribute("employeeData", employee);
		return "admin_view_attendance";
	}

	/**
	 * Admin searches particular employee and displays its details based on employee
	 * id.
	 */
	@RequestMapping(value = "/searchEmployee", method = RequestMethod.POST)
	public ModelAndView searchEmployee(Employee employee, Model model) {
		Employee employee1 = new Employee();
		model.addAttribute("searchEmployee", employee1);
		model.addAttribute("employeeData", employee1);
		ModelAndView mav = new ModelAndView("admin_view_attendance");
		List<Employee> employeeList = attendanceService.searchEmployee(employee);
		if (employeeList.size() > 0)
			mav.addObject("employeeList", employeeList);
		else
			mav.addObject("message", "Invalid Data");
		return mav;
	}

	/**
	 * Admin redirected to attendance page where from and to date form available.
	 */
	@RequestMapping("/displayAttendance")
	public ModelAndView displayAttendance(Model model, Employee employee) {
		ModelAndView mav = new ModelAndView("display_attendance");
		SearchAttendance searchAttendance = new SearchAttendance();
		model.addAttribute("searchAttendance", searchAttendance);
		mav.addObject("employee_id", employee.getEmployeeId());
		return mav;
	}

	/**
	 * Admin gets the attendance details from the database using employee id, from
	 * and to date in a table.
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchAttendance", method = RequestMethod.POST)
	public ModelAndView searchAttendance(SearchAttendance searchAttendance,
			@RequestParam("employeeId") String employeeId) {
		List list = attendanceService.searchAttendance(searchAttendance);
		ModelAndView mav = new ModelAndView("display_attendance");
		mav.addObject("employee_id", employeeId);
		mav.addObject("attendanceList", list.get(0));
		mav.addObject("totalDaysList", list.get(1));
		return mav;
	}

	/**
	 * View for employee to check their attendance. Contains from and to date forms.
	 */
	@RequestMapping("/employee_view_attendance")
	public String employee_view_attendance(Model model) {
		model.addAttribute("searchAttendance", new SearchAttendance());
		return "employee_view_attendance";

	}

	/**
	 * Attendance details of employee's shown in table based on their from and to
	 * date.
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/employeeSearchAttendance")
	public ModelAndView employeeSearchAttendance(SearchAttendance searchAttendance) {
		List attendanceList = attendanceService.employeeSearchAttendance(searchAttendance,
				"1" /* Get Employee ID from session */);
		ModelAndView mav = new ModelAndView("employee_view_attendance");
		mav.addObject("searchAttendance", new SearchAttendance());
		mav.addObject("attendanceList", attendanceList.get(0));
		mav.addObject("totalDaysList", attendanceList.get(1));
		return mav;
	}

	/**
	 * View Mapping to Home page of Admin.
	 */
	@RequestMapping("/admin_home")
	public String admin_home() {
		return "admin_home";
	}
	
	/**
	 * Back button for all the pages in attendance module.
	 */
}
