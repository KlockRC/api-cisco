package org.ruan.cesar.adapters.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ruan.cesar.adapters.auth.HashService;
import org.ruan.cesar.adapters.mapper.UserMapper;
import org.ruan.cesar.adapters.persistence.UserJpaEntity;
import org.ruan.cesar.aplication.AuthRepository;
import org.ruan.cesar.domain.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

@ApplicationScoped
public class PostgresAuthRepository implements AuthRepository {

    private final EntityManager entityManager;
    private final HashService hashService;

    public PostgresAuthRepository(EntityManager entityManager, HashService hashService){
        this.hashService = hashService;
        this.entityManager = entityManager;
    }

    @Override
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        return hashService.hash(password);
    }

    public Boolean checkUser(String username, String password) throws NoSuchAlgorithmException {
        var user = entityManager.createQuery("SELECT u FROM UserJpaEntity u WHERE u.user = :nome", UserJpaEntity.class)
                .setParameter("nome", username)
                .getResultStream()
                .findFirst()
                .orElse(null);
        if (user == null) return false;
        return hashService.compareHash(password, user.getPassword());
    }

    public User findByUsername(String username) {
        var user = entityManager.createQuery("SELECT u FROM UserJpaEntity u WHERE u.user = :nome", UserJpaEntity.class)
                .setParameter("nome", username)
                .getResultStream()
                .findFirst()
                .orElse(null);
        if (user == null) return null;
        return UserMapper.toDomain(user);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        if (user.getUsername().isBlank() || user.getPassword().isBlank()) return;
        entityManager.persist(UserMapper.toEntity(user));
    }
}
