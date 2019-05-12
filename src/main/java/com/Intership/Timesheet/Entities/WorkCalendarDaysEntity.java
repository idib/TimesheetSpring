package com.Intership.Timesheet.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "work_calendar_days", schema = "public", catalog = "TimeSheet")
public class WorkCalendarDaysEntity {
	private int id;
	private int day;
	private MonthsEntity month;
	private AssessmentEntity assessment;
	private EmployeeEntity employee;

	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "day")
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WorkCalendarDaysEntity that = (WorkCalendarDaysEntity) o;
		return id == that.id &&
				day == that.day;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, day);
	}

	@ManyToOne
	@JoinColumn(name = "months_id", referencedColumnName = "id", nullable = false)
	public MonthsEntity getMonth() {
		return month;
	}

	public void setMonth(MonthsEntity monthsByMonthsId) {
		this.month = monthsByMonthsId;
	}

	@ManyToOne
	@JoinColumn(name = "assessment_id", referencedColumnName = "id", nullable = false)
	public AssessmentEntity getAssessment() {
		return assessment;
	}

	public void setAssessment(AssessmentEntity assessmentByAssessmentId) {
		this.assessment = assessmentByAssessmentId;
	}

	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employeeByEmployeeId) {
		this.employee = employeeByEmployeeId;
	}
}
