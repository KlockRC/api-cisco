package org.ruan.cesar.adapters.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.ruan.cesar.adapters.persistence.UserJpaEntity;
import org.ruan.cesar.domain.User;

@ApplicationScoped
public class UserMapper {

    public static UserJpaEntity toEntity(User user) {
       var entity = new UserJpaEntity();
       entity.setUser(user.getUsername());
       entity.setPassword(user.getPassword());
       entity.setRole(user.getRole());
       entity.setId(user.getId());
       return entity;
    }

    public static User toDomain(UserJpaEntity entity) {
       return new User(entity.getId(), entity.getRole(), entity.getUser(), entity.getPassword());
    }
}
