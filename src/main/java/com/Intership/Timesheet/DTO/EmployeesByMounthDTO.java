package com.Intership.Timesheet.DTO;

import java.util.List;

public class EmployeesByMounthDTO {
	private int id;
	private String name;
	private String surname;
	private String patronymic;
	private String position;
	private List<AssassmentCellDTO> workCalendarDaysEntityList;
	private List<ResultAssesmentDTO> result;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<AssassmentCellDTO> getWorkCalendarDaysEntityLint() {
		return workCalendarDaysEntityList;
	}

	public void setWorkCalendarDaysEntityLint(List<AssassmentCellDTO> workCalendarDaysEntityLint) {
		this.workCalendarDaysEntityList = workCalendarDaysEntityLint;
	}

	public List<ResultAssesmentDTO> getResult() {
		return result;
	}

	public void setResult(List<ResultAssesmentDTO> result) {
		this.result = result;
	}
}
