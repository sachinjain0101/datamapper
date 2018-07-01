package com.bullhorn.orm.refreshWork.dao;

import com.bullhorn.orm.refreshWork.model.TblIntegrationMappedMessages;
import com.bullhorn.orm.refreshWork.model.TblIntegrationValidatedMessages;
import com.bullhorn.orm.timecurrent.dao.TimeCurrentDAOExt;
import com.bullhorn.orm.timecurrent.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefreshWorkDAOExtImpl implements RefreshWorkDAOExt {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeCurrentDAOExt.class);

    private final EntityManager em;
    private final JdbcTemplate jdbcTemplate;

    public RefreshWorkDAOExtImpl(@Qualifier("refreshWorkJdbcTemplate") JdbcTemplate jdbcTemplate
            ,@Qualifier("refreshWorkEntityManager") EntityManager em) {
        this.jdbcTemplate = jdbcTemplate;
        this.em = em;
    }

    @Override
    public void batchInsertMappedMessages(List<TblIntegrationMappedMessages> msgs) {
        String sql = "INSERT INTO tblIntegration_ValidatedMessages " +
                "(Client, IntegrationKey, MessageId, SequenceNumber, Status, ErrorDescription, MapName, " +
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
                        ps.setString(5, msgs.get(i).getStatus());
                        ps.setString(6, msgs.get(i).getErrorDescription());
                        ps.setString(7, msgs.get(i).getMapName());
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
    public List<TblIntegrationValidatedMessages> findAllValidated(HashMap<String, Client> clients) {
        LOGGER.debug("Getting validated messages");
        List<TblIntegrationValidatedMessages> validatedMessages = new ArrayList<>();

        for (Map.Entry<String, Client> entry : clients.entrySet()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<TblIntegrationValidatedMessages> cq = cb.createQuery(TblIntegrationValidatedMessages.class);
            Root<TblIntegrationValidatedMessages> root = cq.from(TblIntegrationValidatedMessages.class);
            cq.where(cb.isNull(root.get("status")));
            cq.orderBy(cb.desc(root.get("recordId")));
            TypedQuery<TblIntegrationValidatedMessages> query = em.createQuery(cq);
            validatedMessages.addAll(query.getResultList());
        }
        return validatedMessages;
    }

    @Override
    public boolean updateAllValidated(List<TblIntegrationValidatedMessages> msgs) {
        LOGGER.debug("Updating validated messages");
        String sql = "UPDATE tblIntegration_ValidatedMessages " +
                "SET Status = ? , ErrorDescription = ? " +
                "WHERE RecordID = ?";
        int[] updateCounts = jdbcTemplate.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, msgs.get(i).getStatus());
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
