package com.bullhorn.orm.timecurrent.dao;

import com.bullhorn.orm.timecurrent.model.TblIntegrationAssignmentProcessor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentProcessorDAO extends CrudRepository<TblIntegrationAssignmentProcessor,Long>, TimeCurrentDAOExt {
}
