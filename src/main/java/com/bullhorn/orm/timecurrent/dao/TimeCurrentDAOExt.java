package com.bullhorn.orm.timecurrent.dao;

import java.util.List;

import com.bullhorn.orm.timecurrent.model.TblIntegrationFrontOfficeSystem;
import com.bullhorn.orm.timecurrent.model.MapVO;

public interface TimeCurrentDAOExt {

	List<MapVO> getMapDetail(String mapName);
	List<TblIntegrationFrontOfficeSystem> findByStatus(boolean status);
}
