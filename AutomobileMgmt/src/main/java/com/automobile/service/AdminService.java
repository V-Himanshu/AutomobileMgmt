package com.automobile.service;

import com.automobile.model.Login;

public interface AdminService {

	boolean checkCredentials(Login login);

	String addAttendance(Login login);

	

}
