package com.bullhorn.persistence.timecurrent.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.bullhorn.persistence.timecurrent.model.MapVO;
import com.bullhorn.persistence.timecurrent.model.TblIntegrationMappings;

public class MapDAOExtImpl implements MapDAOExt {

	@PersistenceContext(unitName = "")
	private EntityManager em;

	@Override
	@Transactional
	public List<MapVO> getMapDetail(String mapName) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MapVO> cq = cb.createQuery(MapVO.class);
		Root<TblIntegrationMappings> root = cq.from(TblIntegrationMappings.class);
		cq.multiselect(root.get("attribute"), root.get("expression"));
		cq.where(cb.equal(root.get("mapName"), mapName));
		cq.orderBy(cb.asc(root.get("recordID")));

		TypedQuery<MapVO> query = em.createQuery(cq);

		return query.getResultList();
	}

}
