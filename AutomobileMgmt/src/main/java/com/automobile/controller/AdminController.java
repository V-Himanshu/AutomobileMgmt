package com.automobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.automobile.model.Login;
import com.automobile.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	/**
	 * Returns employee_enter_attendance.jsp page.
	 */
	@RequestMapping("/employee_enter_attendance")
	public String send(Model model) {
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

}
