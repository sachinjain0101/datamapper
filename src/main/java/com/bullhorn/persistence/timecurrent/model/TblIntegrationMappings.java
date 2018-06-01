/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bullhorn.persistence.timecurrent.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sachin.jain
 */
@Entity
@Table(name = "tblIntegration_Mappings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIntegrationMappings.findAll", query = "SELECT t FROM TblIntegrationMappings t")
    , @NamedQuery(name = "TblIntegrationMappings.findByRecordID", query = "SELECT t FROM TblIntegrationMappings t WHERE t.recordID = :recordID")
    , @NamedQuery(name = "TblIntegrationMappings.findByMapName", query = "SELECT t FROM TblIntegrationMappings t WHERE t.mapName = :mapName")
    , @NamedQuery(name = "TblIntegrationMappings.findByAttribute", query = "SELECT t FROM TblIntegrationMappings t WHERE t.attribute = :attribute")
    , @NamedQuery(name = "TblIntegrationMappings.findByExpression", query = "SELECT t FROM TblIntegrationMappings t WHERE t.expression = :expression")
    , @NamedQuery(name = "TblIntegrationMappings.findByVersion", query = "SELECT t FROM TblIntegrationMappings t WHERE t.version = :version")
    , @NamedQuery(name = "TblIntegrationMappings.findByCreatedBy", query = "SELECT t FROM TblIntegrationMappings t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TblIntegrationMappings.findByCreateDateTime", query = "SELECT t FROM TblIntegrationMappings t WHERE t.createDateTime = :createDateTime")})
public class TblIntegrationMappings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RecordID")
    private Integer recordID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MapName")
    private String mapName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Attribute")
    private String attribute;
    @Size(max = 500)
    @Column(name = "Expression")
    private String expression;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Version")
    private int version;
    @Size(max = 100)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CreateDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;

    public TblIntegrationMappings() {
    }

    public TblIntegrationMappings(Integer recordID) {
        this.recordID = recordID;
    }

    public TblIntegrationMappings(Integer recordID, String mapName, String attribute, int version) {
        this.recordID = recordID;
        this.mapName = mapName;
        this.attribute = attribute;
        this.version = version;
    }

    public Integer getRecordID() {
        return recordID;
    }

    public void setRecordID(Integer recordID) {
        this.recordID = recordID;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordID != null ? recordID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblIntegrationMappings)) {
            return false;
        }
        TblIntegrationMappings other = (TblIntegrationMappings) object;
        if ((this.recordID == null && other.recordID != null) || (this.recordID != null && !this.recordID.equals(other.recordID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bullhorn.persistence.timecurrent.model.TblIntegrationMappings[ recordID=" + recordID + " ]";
    }
    
}
