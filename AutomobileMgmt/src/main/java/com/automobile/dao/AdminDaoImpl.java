package com.automobile.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
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
		
		//Bug: It doesn't update the previous day out time and worked hours if user 
		//entered work place on one day and leaves the work place another day

		/**
		 * Check for valid user credentials.
		 */
		String verify = "select * from login where employee_id = ? and password = ?";
		List<GetAttendance> list1 = jdbcTemplate.query(verify,
				new Object[] { getAttendance.getUsername(), getAttendance.getPassword() },
				new RowMapper<GetAttendance>() {
					public GetAttendance mapRow(ResultSet rs, int rowNum) throws SQLException {
						GetAttendance login = new GetAttendance();
						login.setUsername("" + rs.getInt("employee_id"));
						login.setPassword(rs.getString("password"));
						return login;
					}

				});
		if (list1.size() == 0) {
			return null;
		}

		String sql = "select * from attendance where employee_id = ? and t_date = ?";
		List<Attendance> list = jdbcTemplate.query(sql,
				new Object[] { getAttendance.getUsername(), java.sql.Date.valueOf(LocalDate.now()) },
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

		/**
		 * Update attendance based on date. If no employee_id found on particular date,
		 * create one or else update out time. After Updating out time, it also updates Worked hours.
		 */

		if (list.size() > 0) {

			LocalTime from = LocalTime.parse(list.get(0).getIn_time());
			LocalTime to = LocalTime.now();
			Duration d = Duration.between(from, to);

			String workedHours = d.toHours() + ":" + d.toMinutes();

			String sql2 = "update attendance set out_time=?, worked_hours=? where employee_id=? and t_date = ?";
			jdbcTemplate.update(sql2, new Object[] { java.sql.Time.valueOf(to), workedHours,
					Integer.parseInt(getAttendance.getUsername()), java.sql.Date.valueOf(LocalDate.now()) });
			System.out.println("out time");

			return "Out time: " + to;

		} else {
			System.out.println("In time");
			String sql1 = "insert into attendance (employee_id,t_date,in_time) values (?,?,?)";
			jdbcTemplate.update(sql1, new Object[] { getAttendance.getUsername(),
					java.sql.Date.valueOf(LocalDate.now()), java.sql.Time.valueOf(LocalTime.now()) });
			return "In time: " + LocalTime.now();
		}

	}

}
