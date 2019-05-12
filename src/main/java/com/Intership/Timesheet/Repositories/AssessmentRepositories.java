package com.Intership.Timesheet.Repositories;

import com.Intership.Timesheet.Entities.AssessmentEntity;
import com.Intership.Timesheet.Entities.EmployeeEntity;
import com.Intership.Timesheet.Entities.MonthsEntity;
import com.Intership.Timesheet.Entities.WorkCalendarDaysEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepositories extends CrudRepository<AssessmentEntity,Integer> {

//	select assessment_id, count(*) from work_calendar_days where employee_id = 1 and months_id = 1 group by assessment_id order by assessment_id
//	List<WorkCalendarDaysEntity> find(EmployeeEntity employeeId, MonthsEntity monthId);
}
