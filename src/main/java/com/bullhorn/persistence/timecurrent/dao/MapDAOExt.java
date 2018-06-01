package com.bullhorn.persistence.timecurrent.dao;

import java.util.List;

import com.bullhorn.persistence.timecurrent.model.MapVO;

public interface MapDAOExt {

	List<MapVO> getMapDetail(String mapName);
	
}
