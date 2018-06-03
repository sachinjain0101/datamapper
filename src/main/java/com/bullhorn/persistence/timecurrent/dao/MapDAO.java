package com.bullhorn.persistence.timecurrent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bullhorn.persistence.timecurrent.model.TblIntegrationMappings;
import org.springframework.stereotype.Repository;

@Repository
public interface MapDAO extends JpaRepository<TblIntegrationMappings, Long>, MapDAOExt{

}
