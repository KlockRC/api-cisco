package org.ruan.cesar.adapters.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ruan.cesar.domain.enums.Roles;


import static jakarta.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_api")
public class UserJpaEntity {
    @Enumerated(STRING)
    private Roles role;
    @Id
    @Column(name = "username")
    private String user;
    private String password;

}
