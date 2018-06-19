package com.bullhorn.orm.timecurrent.dao;

import com.bullhorn.orm.timecurrent.model.TblIntegrationMappings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapDAO extends JpaRepository<TblIntegrationMappings, Long>, TimeCurrentDAOExt {

}
