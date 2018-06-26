package com.bullhorn.orm.timecurrent.dao;

import java.util.List;
import java.util.Map;

import com.bullhorn.orm.timecurrent.model.MapVO;
import com.bullhorn.orm.timecurrent.model.TblIntegrationErrors;

public interface TimeCurrentDAOExt {

	List<MapVO> getMapDetail(String mapName);
    Map<String,String> getIntegrationConfig();
}
