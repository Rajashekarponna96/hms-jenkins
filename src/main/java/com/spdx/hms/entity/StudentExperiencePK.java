package com.spdx.hms.entity;

import javax.persistence.*;
import java.io.Serializable;
@Embeddable
public class StudentExperiencePK implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="std_expr_id")
    Long stdExprId;
    @Column(name="std_profile_id")
    Long stdProfileId;
}
