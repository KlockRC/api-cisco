package org.ruan.cesar.domain;

import lombok.Getter;
import org.ruan.cesar.domain.enums.Roles;

@Getter
public class User {

    private final Long id;
    private Roles role;
    private final String username;
    private String password;

    public User(Long id,Roles role,String username, String password) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
    }
}
