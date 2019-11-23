package com.automobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.automobile.model.GetAttendance;
import com.automobile.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/{path}", method = RequestMethod.GET)
	public String redirect(@PathVariable String path) {
		return path;
	}
	
	@RequestMapping("/employee_enter_attendance")
	public String send(Model model) {
		GetAttendance attendance = new GetAttendance();
		model.addAttribute("attendance", attendance);
		return "employee_enter_attendance";
	}

	@RequestMapping(value = "/processAttendance", method = RequestMethod.POST)
	public ModelAndView processAttendance(GetAttendance attendance) {
		System.out.println(attendance);
		String time = adminService.getTime(attendance);
		if(time == null) {
			ModelAndView mav = new ModelAndView("employee_enter_attendance");
			GetAttendance login = new GetAttendance();
			mav.addObject("attendance", login);
			mav.addObject("message", "Invalid Username or Password!");
			return mav;
		}
			
		ModelAndView mav = new ModelAndView("show_attendance");
//		GetAttendance attendance2 = new GetAttendance();
//		mav.addObject("attendance", attendance2);
		mav.addObject("displayTime", time);
		return mav;
	}
}
