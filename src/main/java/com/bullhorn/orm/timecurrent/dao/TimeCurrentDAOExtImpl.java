package com.bullhorn.orm.timecurrent.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.bullhorn.orm.timecurrent.model.MapVO;
import com.bullhorn.orm.timecurrent.model.TblIntegrationFrontOfficeSystem;
import com.bullhorn.orm.timecurrent.model.TblIntegrationMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeCurrentDAOExtImpl implements TimeCurrentDAOExt {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeCurrentDAOExt.class);

	@PersistenceContext(unitName = "")
	private EntityManager em;

	@Override
	@Transactional
	public List<MapVO> getMapDetail(String mapName) {
		LOGGER.info("Getting data for - {}",mapName);
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
	@Transactional
	public List<TblIntegrationFrontOfficeSystem> findByStatus(boolean status) {
		LOGGER.info("Getting data for status - {}",status);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TblIntegrationFrontOfficeSystem> cq = cb.createQuery(TblIntegrationFrontOfficeSystem.class);
		Root<TblIntegrationFrontOfficeSystem> root = cq.from(TblIntegrationFrontOfficeSystem.class);
		cq.where(cb.equal(root.get("recordStatus"), status));
		//cq.orderBy(cb.asc(root.get("recordId")));

		TypedQuery<TblIntegrationFrontOfficeSystem> query = em.createQuery(cq);

		return query.getResultList();
	}
}
