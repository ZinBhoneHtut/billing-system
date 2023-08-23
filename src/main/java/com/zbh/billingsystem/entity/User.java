package com.zbh.billingsystem.entity;

import com.zbh.billingsystem.entity.audit.Audit;
import com.zbh.billingsystem.entity.audit.AuditListener;
import com.zbh.billingsystem.entity.audit.IAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author ZinBhoneHtut
 */
@Data
@Builder
@Entity
@Table(name = "user_info", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "email"})
})
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditListener.class)
public class User implements Serializable, IAudit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "email", length = 80)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Audit audit;

    public User(String name, String email, String phoneNumber, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
