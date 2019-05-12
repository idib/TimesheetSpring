package com.Intership.Timesheet.Repositories;

import com.Intership.Timesheet.Entities.EmployeeEntity;
import com.Intership.Timesheet.Entities.MonthsEntity;
import com.Intership.Timesheet.Entities.WorkCalendarDaysEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository

public interface WorkCalendarDaysRepositories extends CrudRepository<WorkCalendarDaysEntity, Integer> {

//	List<WorkCalendarDaysEntity> findByMonthsByMonthsIdAndEmployeeByEmployeeIdOrderByDay(MonthsEntity monthsEntity, EmployeeEntity employeeEntity);


	List<WorkCalendarDaysEntity> findWorkCalendarDaysEntitiesByEmployeeAndMonth(EmployeeEntity employeeId, MonthsEntity monthId);
}
