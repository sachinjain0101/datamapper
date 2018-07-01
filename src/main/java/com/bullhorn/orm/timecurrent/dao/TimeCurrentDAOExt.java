package com.bullhorn.orm.timecurrent.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bullhorn.orm.timecurrent.model.Client;
import com.bullhorn.orm.timecurrent.model.MapVO;
import com.bullhorn.orm.timecurrent.model.TblIntegrationErrors;
import com.bullhorn.orm.timecurrent.model.TblIntegrationFrontOfficeSystem;

public interface TimeCurrentDAOExt {

    List<TblIntegrationFrontOfficeSystem> findByStatus(boolean status, String cluster);
    HashMap<String,Client> getAllActiveClients(int frontOfficeSystemRecordID);
	List<MapVO> getMapDetail(String mapName);
    Map<String,String> getIntegrationConfig();
}
