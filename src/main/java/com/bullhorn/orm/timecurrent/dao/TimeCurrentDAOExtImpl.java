package com.bullhorn.orm.timecurrent.dao;

import com.bullhorn.orm.timecurrent.model.MapVO;
import com.bullhorn.orm.timecurrent.model.TblIntegrationErrors;
import com.bullhorn.orm.timecurrent.model.TblIntegrationMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeCurrentDAOExtImpl implements TimeCurrentDAOExt {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeCurrentDAOExt.class);

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final EntityManager em;

	public TimeCurrentDAOExtImpl(@Qualifier("timeCurrentJdbcTemplate") JdbcTemplate jdbcTemplate
			,@Qualifier("timeCurrentNamedJdbcTemplate") NamedParameterJdbcTemplate namedParameterJdbcTemplate
			,@Qualifier("timeCurrentEntityManager") EntityManager em) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.em = em;
	}

	@Override
	public List<MapVO> getMapDetail(String mapName) {
		LOGGER.debug("Getting data for - {}",mapName);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MapVO> cq = cb.createQuery(MapVO.class);
		Root<TblIntegrationMappings> root = cq.from(TblIntegrationMappings.class);
		cq.multiselect(root.get("attribute"), root.get("expression"));
		cq.where(cb.equal(root.get("mapName"), mapName));
		cq.orderBy(cb.asc(root.get("recordID")));

		TypedQuery<MapVO> query = em.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public Map<String,String> getIntegrationConfig(){
		String sqlStr = "SELECT * FROM TimeCurrent.dbo.tblIntegration_Config ORDER BY RecordID DESC";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlStr);
		Map<String,String> config = new HashMap<>();
		for(Map row:rows){

			config.put(row.get("CfgKey").toString(),row.get("CfgValue").toString());
		}
		return config;
	}
}
