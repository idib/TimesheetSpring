package com.Intership.Timesheet.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "year", schema = "public", catalog = "TimeSheet")
public class YearEntity {
	private int id;
	private int name;

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
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		YearEntity that = (YearEntity) o;
		return id == that.id &&
				name == that.name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
