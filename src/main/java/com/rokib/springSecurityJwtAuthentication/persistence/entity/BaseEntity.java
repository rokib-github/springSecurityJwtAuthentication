package com.rokib.springSecurityJwtAuthentication.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date createdAt;

    @Column(name = "created_by")
    @JsonIgnore
    private Long createdBy;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date updatedAt;

    @Column(name = "updated_by")
    @JsonIgnore
    private Long updatedBy;

    /**
     * Sets CreatedBy and createdAt before insert
     */
    @PrePersist
    public void setCreationInfo() {
        this.createdAt = new Date();
    }

    /**
     * Sets UpdatedBy and updatedAt before update
     */
    @PreUpdate
    public void setUpdateInfo() {
        this.updatedAt = new Date();
    }
}
