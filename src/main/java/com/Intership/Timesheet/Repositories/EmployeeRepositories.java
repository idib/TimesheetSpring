package com.Intership.Timesheet.Repositories;

import com.Intership.Timesheet.Entities.DepartmentEntity;
import com.Intership.Timesheet.Entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepositories extends CrudRepository<EmployeeEntity, Integer> {

	List<EmployeeEntity> findAll();

	List<EmployeeEntity> findEmployeeEntitiesByDepartment(DepartmentEntity departmentEntity);
}
