package org.ruan.cesar.adapters.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ruan.cesar.adapters.auth.HashService;
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
        var user = entityManager.find(UserJpaEntity.class, username);
        if (user == null) return false;
        return hashService.compareHash(password, user.getPassword());
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        if (user.getUsername().isBlank() || user.getPassword().isBlank()) return;
        var userJpa = new UserJpaEntity(user.getRole(), user.getUsername(), user.getPassword());
        entityManager.persist(userJpa);
    }
}
