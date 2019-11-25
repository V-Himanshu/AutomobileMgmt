package com.automobile.model;

public class Attendance {

	private String employeeId;
	private String inDate;
	private String inTime;
	private String outDate;
	private String outTime;
	private String workedHours;
	private String activeStatus;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(String workedHours) {
		this.workedHours = workedHours;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		return "Attendance [employeeId=" + employeeId + ", inDate=" + inDate + ", inTime=" + inTime + ", outDate="
				+ outDate + ", outTime=" + outTime + ", workedHours=" + workedHours + ", activeStatus=" + activeStatus
				+ "]";
	}

}
