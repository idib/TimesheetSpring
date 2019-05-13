package com.Intership.Timesheet.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "months", schema = "public", catalog = "TimeSheet")
public class MonthsEntity {
	private int id;
	private String name;
	private int countDay;
	private YearEntity year;

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
	@Column(name = "count_day")
	public int getCountDay() {
		return countDay;
	}

	public void setCountDay(int countDay) {
		this.countDay = countDay;
	}

	@ManyToOne
	@JoinColumn(name = "year_id", referencedColumnName = "id", nullable = false)
	public YearEntity getYear() {
		return year;
	}

	public void setYear(YearEntity yearEntity) {
		this.year = yearEntity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MonthsEntity that = (MonthsEntity) o;
		return id == that.id &&
				countDay == that.countDay &&
				Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, countDay);
	}
}
