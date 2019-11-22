package com.automobile.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.automobile.dao.AdminDao;
import com.automobile.model.GetAttendance;

public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDao adminDao;

	public String getTime(GetAttendance attendance) {
		return adminDao.getTime(attendance);
		
	}

}
