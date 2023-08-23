package com.zbh.billingsystem.entity;

import com.zbh.billingsystem.entity.audit.Audit;
import com.zbh.billingsystem.entity.audit.AuditListener;
import com.zbh.billingsystem.entity.audit.IAudit;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bill")
@Builder
@Getter
@Setter
@ToString(exclude = {"transactionList", "audit"})
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class Bill implements Serializable, IAudit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "bill")
    private List<Transaction> transactionList = new ArrayList<>();

    @Embedded
    private Audit audit;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bill bill = (Bill) o;
        return Objects.equals(name, bill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
