package com.spdx.hms.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class AuditableEntity implements Serializable {
    public static final long serialVersionUID = 1L;
    @Column(name = "created_by")
    String createdBy;
    @Column(name = "modified_by")
    String modifiedBy;
    @Column(name = "created_timestamp")
    Long createdTimestamp;
    @Column(name = "modified_timestamp")
    Long modifiedTimestamp;
}
