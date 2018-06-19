package com.bullhorn.orm.refreshWork.dao;

import com.bullhorn.orm.refreshWork.model.TblIntegrationServiceBusMessages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappedMessagesDAO extends CrudRepository<TblIntegrationServiceBusMessages,Long>,RefreshWorkDAOExt {
}
