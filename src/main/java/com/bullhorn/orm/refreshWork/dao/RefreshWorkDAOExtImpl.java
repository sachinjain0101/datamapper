package com.bullhorn.orm.refreshWork.dao;

import com.bullhorn.orm.refreshWork.model.TblIntegrationMappedMessages;
import com.bullhorn.orm.refreshWork.model.TblIntegrationServiceBusMessages;
import com.bullhorn.orm.refreshWork.model.TblIntegrationValidatedMessages;
import com.bullhorn.orm.timecurrent.dao.TimeCurrentDAOExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RefreshWorkDAOExtImpl implements RefreshWorkDAOExt {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeCurrentDAOExt.class);

    @Autowired
    @Qualifier("refreshWorkJdbcTemplate")
    JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("refreshWorkEntityManager")
    EntityManager em;

    @Override
    public void batchInsertMappedMessages(List<TblIntegrationMappedMessages> msgs) {
        String sql = "INSERT INTO tblIntegration_ValidatedMessages " +
                "(Client, IntegrationKey, MessageId, SequenceNumber, Processed, ErrorDescription, Map, " +
                " Message, MappedMessage, NoOfAssignments, FrontOfficeSystemRecordID, ClientRecordID, ServiceBusMessagesRecordID, ValidatedMessagesRecordID) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int[] updateCounts = jdbcTemplate.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, msgs.get(i).getClient());
                        ps.setString(2, msgs.get(i).getIntegrationKey());
                        ps.setString(3, msgs.get(i).getMessageId());
                        ps.setLong(4, msgs.get(i).getSequenceNumber());
                        ps.setInt(5, msgs.get(i).getProcessed());
                        ps.setString(6, msgs.get(i).getErrorDescription());
                        ps.setString(7, msgs.get(i).getMap());
                        ps.setString(8, msgs.get(i).getMessage());
                        ps.setString(9, msgs.get(i).getMappedMessage());
                        ps.setInt(10, msgs.get(i).getNoOfAssignments());
                        ps.setInt(11, msgs.get(i).getFrontOfficeSystemRecordID());
                        ps.setInt(12, msgs.get(i).getClientRecordID());
                        ps.setLong(13, msgs.get(i).getServiceBusMessagesRecordID());
                        ps.setLong(14, msgs.get(i).getValidatedMessagesRecordID());
                    }

                    public int getBatchSize() {
                        return msgs.size();
                    }
                });
    }

    @Override
    public List<TblIntegrationValidatedMessages> findAllValidated() {
        LOGGER.info("Getting downloaded messages");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TblIntegrationValidatedMessages> cq = cb.createQuery(TblIntegrationValidatedMessages.class);
        Root<TblIntegrationValidatedMessages> root = cq.from(TblIntegrationValidatedMessages.class);
        cq.where(cb.isNull(root.get("processed")));
        cq.orderBy(cb.desc(root.get("recordId")));

        TypedQuery<TblIntegrationValidatedMessages> query = em.createQuery(cq);

        return query.getResultList();

    }

    @Override
    public boolean updateAllValidated(List<TblIntegrationValidatedMessages> msgs) {
        LOGGER.info("Updating downloaded messages");
        String sql = "UPDATE tblIntegration_ValidatedMessages " +
                "SET Processed = ? , ErrorDescription = ? " +
                "WHERE RecordID = ?";
        int[] updateCounts = jdbcTemplate.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, msgs.get(i).getProcessed());
                        ps.setString(2, msgs.get(i).getErrorDescription());
                        ps.setLong(3, msgs.get(i).getRecordId());
                    }

                    public int getBatchSize() {
                        return msgs.size();
                    }
                });

        return false;
    }

}
