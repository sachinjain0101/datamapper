package com.bullhorn.orm.refreshWork.dao;

import com.bullhorn.orm.refreshWork.model.TblIntegrationMappedMessages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappedMessagesDAO extends CrudRepository<TblIntegrationMappedMessages,Long>,RefreshWorkDAOExt {
}
