package com.Intership.Timesheet.Repositories;

import com.Intership.Timesheet.Entities.MonthsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthsRepositories extends CrudRepository<MonthsEntity, Integer> {
}
