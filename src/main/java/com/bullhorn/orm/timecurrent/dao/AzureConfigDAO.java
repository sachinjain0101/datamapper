package com.bullhorn.orm.timecurrent.dao;

import com.bullhorn.orm.timecurrent.model.TblIntegrationFrontOfficeSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AzureConfigDAO extends JpaRepository<TblIntegrationFrontOfficeSystem,Long>, TimeCurrentDAOExt {
}
