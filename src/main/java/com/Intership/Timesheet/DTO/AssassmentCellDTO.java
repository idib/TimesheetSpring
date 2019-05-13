package com.Intership.Timesheet.DTO;

import java.util.List;

public class AssassmentCellDTO {
	private String path;
	private int day;
	private String selectedValue;
	private List<AssassmentDTO> assassmentList;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	public List<AssassmentDTO> getAssassmentList() {
		return assassmentList;
	}

	public void setAssassmentList(List<AssassmentDTO> assassmentList) {
		this.assassmentList = assassmentList;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
