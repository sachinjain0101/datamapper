package com.bullhorn.orm.timecurrent.dao;

import java.util.List;

import com.bullhorn.orm.timecurrent.model.MapVO;
import com.bullhorn.orm.timecurrent.model.TblIntegrationErrors;

public interface TimeCurrentDAOExt {

	List<MapVO> getMapDetail(String mapName);
	void insertError(TblIntegrationErrors error);

	void insertIntoTblIntegrationsErrors(TblIntegrationErrors error);
}
