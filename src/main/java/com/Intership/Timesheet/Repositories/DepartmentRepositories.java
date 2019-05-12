package com.Intership.Timesheet.Repositories;

import com.Intership.Timesheet.Entities.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepositories extends CrudRepository<DepartmentEntity, Integer> {
}