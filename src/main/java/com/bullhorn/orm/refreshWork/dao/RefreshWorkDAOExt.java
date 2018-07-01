package com.bullhorn.orm.refreshWork.dao;

import com.bullhorn.orm.refreshWork.model.TblIntegrationMappedMessages;
import com.bullhorn.orm.refreshWork.model.TblIntegrationValidatedMessages;
import com.bullhorn.orm.timecurrent.model.Client;

import java.util.HashMap;
import java.util.List;

public interface RefreshWorkDAOExt {

    List<TblIntegrationValidatedMessages> findAllValidated(HashMap<String, Client> clients);

    boolean updateAllValidated(List<TblIntegrationValidatedMessages> tblIntegrationValidatedMessages);

    void batchInsertMappedMessages(List<TblIntegrationMappedMessages> msgs);
}
