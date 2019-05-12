package com.Intership.Timesheet.Repositories;

import com.Intership.Timesheet.Entities.MonthsEntity;
import com.Intership.Timesheet.Entities.YearEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthsRepositories extends CrudRepository<MonthsEntity, Integer> {

	List<MonthsEntity> findMonthsEntitiesByYear(YearEntity yearEntity);
}
