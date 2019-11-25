package com.automobile.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.automobile.model.Attendance;
import com.automobile.model.Login;

public class AdminDaoImpl implements AdminDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * Check Login credentials to provide attendance for intended users.
	 */
	public boolean checkCredentials(Login login) {

		String sql = "Select * from login where employee_id=? and password=?";
		List<Login> loginList = jdbcTemplate.query(sql, new Object[] { login.getUsername(), login.getPassword() },
				new RowMapper<Login>() {

					public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
						Login credentials = new Login();
						credentials.setUsername(rs.getString("employee_id"));
						credentials.setPassword(rs.getString("password"));
						return credentials;
					}
				});
		if (loginList.size() > 0)
			return true;
		return false;

	}

	/**
	 * Return list with employee values to check whether In-date for employee
	 * already updated or not. If not done, list returns null values.
	 */
	public List<Attendance> checkAttendance(Login login, LocalDate date) {
		String sql = "select * from attendance where employee_id=? and in_date=?";
		List<Attendance> attendance = jdbcTemplate.query(sql, new Object[] { login.getUsername(), "" + date },
				new RowMapper<Attendance>() {

					public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
						Attendance attendance = new Attendance();
						attendance.setEmployeeId(rs.getString("employee_id"));
						attendance.setInDate(rs.getString("in_date"));
						attendance.setInTime(rs.getString("in_time"));
						attendance.setOutDate(rs.getString("out_date"));
						attendance.setOutTime(rs.getString("out_time"));
						attendance.setWorkedHours(rs.getString("worked_hours"));
						attendance.setActiveStatus(rs.getString("active_status"));
						return attendance;
					}
				});
		return attendance;

	}

	/**
	 * Inserting new row of employee for particular day's attendance.
	 */
	public void updateInDetails(Login login, LocalDate localDate, LocalTime localTime) {
		String sql = "insert into attendance(employee_id, in_date, in_time) values(?,?,?)";
		jdbcTemplate.update(sql, new Object[] { login.getUsername(), "" + localDate, "" + localTime });
	}

	/**
	 * Updating attendance of employee with Out-date, Out-time, active_status and
	 * workedHours.
	 */
	public void updateOutDetails(Login login, LocalDate localDate, LocalTime localTime, String workedHours) {
		String sql = "update attendance set out_date=?, out_time=?, worked_hours=?, active_status='n' where employee_id=?";
		jdbcTemplate.update(sql, new Object[] { "" + localDate, "" + localTime, workedHours, login.getUsername() });
	}

}
