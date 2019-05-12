package com.Intership.Timesheet.Entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "public", catalog = "TimeSheet")
public class EmployeeEntity {
	private int id;
	private String name;
	private String surname;
	private String patronymic;
	private String position;
	private DepartmentEntity departmentByDepartmentId;

	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Basic
	@Column(name = "patronymic")
	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	@Basic
	@Column(name = "position")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmployeeEntity that = (EmployeeEntity) o;
		return id == that.id &&
				Objects.equals(name, that.name) &&
				Objects.equals(surname, that.surname) &&
				Objects.equals(patronymic, that.patronymic) &&
				Objects.equals(position, that.position);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, surname, patronymic, position);
	}

	@ManyToOne
	@JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
	public DepartmentEntity getDepartmentByDepartmentId() {
		return departmentByDepartmentId;
	}

	public void setDepartmentByDepartmentId(DepartmentEntity departmentByDepartmentId) {
		this.departmentByDepartmentId = departmentByDepartmentId;
	}
}
