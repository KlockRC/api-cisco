package org.ruan.cesar.adapters.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.ruan.cesar.domain.enums.Roles;


import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_api")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Roles role;

    @Column(name = "username", unique = true, nullable = false)
    private String user;

    @Column(nullable = false)
    private String password;

}
