package com.zbh.billingsystem.entity;

import com.zbh.billingsystem.entity.audit.Audit;
import com.zbh.billingsystem.entity.audit.AuditListener;
import com.zbh.billingsystem.entity.audit.IAudit;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transaction")
@Data
@ToString(exclude = "audit")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class Transaction implements Serializable, IAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reference_no", unique = true, nullable = false)
    private String referenceNo;

    @Column(name = "api_caller", nullable = false)
    private String apiCaller;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "amount")
    private long amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    @Embedded
    private Audit audit;

}
