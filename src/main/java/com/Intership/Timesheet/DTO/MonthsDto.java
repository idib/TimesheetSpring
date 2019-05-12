package com.Intership.Timesheet.DTO;

import java.util.List;

public class MonthsDto {
	private List<EmployeesByMounthDTO> employeesByMounthDTOS;
	private String name;
	private List<Integer> listDays;
	private boolean isActive;

	public List<EmployeesByMounthDTO> getEmployeesByMounthDTOS() {
		return employeesByMounthDTOS;
	}

	public void setEmployeesByMounthDTOS(List<EmployeesByMounthDTO> employeesByMounthDTOS) {
		this.employeesByMounthDTOS = employeesByMounthDTOS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getListDays() {
		return listDays;
	}

	public void setListDays(List<Integer> listDays) {
		this.listDays = listDays;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}
}
