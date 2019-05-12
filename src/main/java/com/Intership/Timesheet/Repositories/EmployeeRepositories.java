package com.Intership.Timesheet.Repositories;

import com.Intership.Timesheet.Entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositories extends CrudRepository<EmployeeEntity, Integer> {
}
