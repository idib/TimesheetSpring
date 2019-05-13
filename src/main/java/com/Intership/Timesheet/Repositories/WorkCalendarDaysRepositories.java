package com.Intership.Timesheet.Repositories;

import com.Intership.Timesheet.Entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository

public interface WorkCalendarDaysRepositories extends CrudRepository<WorkCalendarDaysEntity, Integer> {

//	List<WorkCalendarDaysEntity> findByMonthsByMonthsIdAndEmployeeByEmployeeIdOrderByDay(MonthsEntity monthsEntity, EmployeeEntity employeeEntity);


	List<WorkCalendarDaysEntity> findWorkCalendarDaysEntitiesByEmployeeAndMonthOrderByDay(EmployeeEntity employeeId, MonthsEntity monthId);

	Optional<WorkCalendarDaysEntity> findWorkCalendarDaysEntityByEmployeeAndMonthAndDay(EmployeeEntity employeeId, MonthsEntity monthId, int day);

//
//	@Query(value = "select assessment_id, count(*) from work_calendar_days where employee_id = 1 and months_id = 1 group by assessment_id order by assessment_id",
//			nativeQuery = true)
//	Map<AssessmentEntity,Integer> countByAssessmentWhereEmployeeAndMonth(EmployeeEntity employeeId, MonthsEntity monthId);


//	@Query("SELECT " +
//			"    new com.Intership.Timesheet.DTO.ResultAssesmentDTO(a.value, count(v)) " +
//			"from work_calendar_days w " +
//			"join assessment a on w.assessment_id = a.id  " +
//			"where " +
//			"      w.employee_id = ?1 and " +
//			"        w.months_id = ?2 " +
//			"GROUP BY " +
//			"    w.assessment_id " +
//			"Order by " +
//			"    w.assessment_id ")
//	List<ResultAssesmentDTO> findResultByEmployeeAndMonth(EmployeeEntity employeeId, MonthsEntity monthId);


}
