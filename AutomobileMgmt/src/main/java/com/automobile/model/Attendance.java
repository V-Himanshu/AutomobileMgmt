package com.automobile.model;

public class Attendance {

	private int employee_id;
	private String t_date;
	private String in_time;
	private String out_time;
	private double worked_hours;

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getT_date() {
		return t_date;
	}

	public void setT_date(String t_date) {
		this.t_date = t_date;
	}

	public String getIn_time() {
		return in_time;
	}

	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}

	public String getOut_time() {
		return out_time;
	}

	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}

	public double getWorked_hours() {
		return worked_hours;
	}

	public void setWorked_hours(double worked_hours) {
		this.worked_hours = worked_hours;
	}

	@Override
	public String toString() {
		return "Attendance [employee_id=" + employee_id + ", t_date=" + t_date + ", in_time=" + in_time + ", out_time="
				+ out_time + ", worked_hours=" + worked_hours + "]";
	}

}
