package com.automobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.automobile.model.Attendance;
import com.automobile.model.Employee;
import com.automobile.model.Login;
import com.automobile.model.SearchAttendance;
import com.automobile.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

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
		if (adminService.checkCredentials(login)) {
			String time = adminService.addAttendance(login);
			ModelAndView mav = new ModelAndView("show_attendance");
			mav.addObject("displayTime", time);
			return mav;
		}
		ModelAndView mav = new ModelAndView("employee_enter_attendance");
		Login newLogin = new Login();
		mav.addObject("attendance", newLogin);
		mav.addObject("message", "Invalid Credentials");
		return mav;
	}

	@RequestMapping("/admin_view_attendance")
	public String adminViewAttendance(Model model) {
		Employee employee = new Employee();
		model.addAttribute("searchEmployee", employee);
		model.addAttribute("employeeData", employee);
		return "admin_view_attendance";
	}

	@RequestMapping(value = "/searchEmployee", method = RequestMethod.POST)
	public ModelAndView searchEmployee(Employee employee, Model model) {
		Employee employee1 = new Employee();
		model.addAttribute("searchEmployee", employee1);
		model.addAttribute("employeeData", employee1);
		ModelAndView mav = new ModelAndView("admin_view_attendance");
		List<Employee> employeeList = adminService.searchEmployee(employee);
		if (employeeList.size() > 0)
			mav.addObject("employeeList", employeeList);
		else
			mav.addObject("message", "Invalid Data");
		return mav;
	}

	@RequestMapping("/displayAttendance")
	public ModelAndView displayAttendance(Model model, Employee employee) {
		ModelAndView mav = new ModelAndView("display_attendance");
		SearchAttendance searchAttendance = new SearchAttendance();
		model.addAttribute("searchAttendance", searchAttendance);
		mav.addObject("employee_id", employee.getEmployeeId());
		return mav;
	}

	@RequestMapping(value = "/searchAttendance", method = RequestMethod.POST)
	public ModelAndView searchAttendance(SearchAttendance searchAttendance) {
		List<Attendance> list = adminService.searchAttendance(searchAttendance);
		ModelAndView mav = new ModelAndView("display_attendance");
		mav.addObject("attendanceList", list);
		return mav;
	}
}
