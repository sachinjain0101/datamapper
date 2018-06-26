package com.bullhorn.orm.refreshWork.dao;

import com.bullhorn.orm.refreshWork.model.TblIntegrationMappedMessages;
import com.bullhorn.orm.refreshWork.model.TblIntegrationValidatedMessages;

import java.util.List;

public interface RefreshWorkDAOExt {

    List<TblIntegrationValidatedMessages> findAllValidated();

    boolean updateAllValidated(List<TblIntegrationValidatedMessages> tblIntegrationValidatedMessages);

    void batchInsertMappedMessages(List<TblIntegrationMappedMessages> msgs);
}
