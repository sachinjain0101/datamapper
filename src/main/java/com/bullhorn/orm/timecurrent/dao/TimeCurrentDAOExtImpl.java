package com.bullhorn.orm.timecurrent.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bullhorn.orm.timecurrent.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class TimeCurrentDAOExtImpl implements TimeCurrentDAOExt {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeCurrentDAOExt.class);

	@Autowired
	@Qualifier("timeCurrentEntityManager")
	private EntityManager em;

	@Override
	@Transactional("timeCurrentTransactionManager")
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
	@Transactional("timeCurrentTransactionManager")
	public void insertError(TblIntegrationErrors error){
		em.persist(error);
	}

	@Autowired
	@Qualifier("timeCurrentJdbcTemplate")
	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("timeCurrentNamedJdbcTemplate")
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void insertIntoTblIntegrationsErrors(TblIntegrationErrors error){
		String insertStr = "INSERT INTO TimeCurrent.dbo.tblIntegration_Errors \n" +
				"(IntegrationKey, Client, FrontOfficeSystemRecordId, ProcessName, MessageId, ErrorSource, ErrorCode, ErrorDescription, CreateDateTime) \n" +
				"VALUES (:integrationKey, :client, :frontOfficeSystemRecordId, :processName, :messageId, :errorSource, :errorCode, :errorDescription, :createDateTime); \n";

		Map namedParameters = new HashMap();
		namedParameters.put("integrationKey", error.getIntegrationKey());
		namedParameters.put("client", error.getClient());
		namedParameters.put("frontOfficeSystemRecordId", error.getFrontOfficeSystemRecordId());
		namedParameters.put("processName", error.getProcessName());
		namedParameters.put("messageId", error.getMessageId());
		namedParameters.put("errorSource", error.getErrorSource());
		namedParameters.put("errorCode", error.getErrorCode());
		namedParameters.put("errorDescription", error.getErrorDescription());
		namedParameters.put("createDateTime", new Date());
		namedParameterJdbcTemplate.update(insertStr, namedParameters);

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
