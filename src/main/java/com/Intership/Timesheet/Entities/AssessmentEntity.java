package com.Intership.Timesheet.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "assessment", schema = "public", catalog = "TimeSheet")
public class AssessmentEntity {
	private int id;
	private String value;

	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AssessmentEntity that = (AssessmentEntity) o;
		return id == that.id &&
				Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, value);
	}
}
