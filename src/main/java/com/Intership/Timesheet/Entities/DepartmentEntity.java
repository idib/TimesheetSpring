package com.Intership.Timesheet.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department", schema = "public", catalog = "TimeSheet")
public class DepartmentEntity {
	private int id;
	private String name;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DepartmentEntity that = (DepartmentEntity) o;
		return id == that.id &&
				Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
