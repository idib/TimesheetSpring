package com.Intership.Timesheet.Repositories;

import com.Intership.Timesheet.Entities.YearEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearRepositories extends CrudRepository<YearEntity, Integer> {
}
