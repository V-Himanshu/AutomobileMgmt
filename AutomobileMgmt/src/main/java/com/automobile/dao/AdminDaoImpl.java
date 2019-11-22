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
import com.automobile.model.GetAttendance;

public class AdminDaoImpl implements AdminDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public String getTime(GetAttendance getAttendance) {
		//First check for valid username and password!!! If correct then proceed-
		// If invalid credentials then display invalid credentials.
		
		
		
		String sql = "select * from attendance where employee_id = ?";
		List<Attendance> list = jdbcTemplate.query(sql, new Object[] { getAttendance.getUsername() },
				new RowMapper<Attendance>() {
					public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
						Attendance attendance = new Attendance();
						attendance.setEmployee_id(rs.getInt("employee_id"));
						attendance.setT_date(rs.getString("t_date"));
						attendance.setIn_time(rs.getString("in_time"));
						attendance.setOut_time(rs.getString("out_time"));
						attendance.setWorked_hours(rs.getDouble("worked_hours"));
						return attendance;
					}

				});
		
		
		// Also, update database using date. If new Date found then new row and intime to be creaed.

		if (list.size() > 0) {

			String sql2 = "update attendance set out_time=? where employee_id=?";
			jdbcTemplate.update(sql2, new Object[] {"" + LocalTime.now(), Integer.parseInt(getAttendance.getUsername())});
			System.out.println("out time");
			
			//After updating out time also calculate the hours worked and insert in database
			return "Out time: " + LocalTime.now();
			
		} else {
			System.out.println("In time");
			String sql1 = "insert into attendance (employee_id,t_date,in_time) values (?,?,?)";
			jdbcTemplate.update(sql1, new Object[] {getAttendance.getUsername(), "" + LocalDate.now(), "" + LocalTime.now()});
			return "In time: " + LocalTime.now();
		}

	}
	
}
