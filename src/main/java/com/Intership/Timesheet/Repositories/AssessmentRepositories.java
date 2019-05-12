package com.Intership.Timesheet.Repositories;

import com.Intership.Timesheet.Entities.AssessmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepositories extends CrudRepository<AssessmentEntity,Integer> {
}
