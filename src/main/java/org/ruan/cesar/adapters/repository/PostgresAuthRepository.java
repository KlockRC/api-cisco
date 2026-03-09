package org.ruan.cesar.adapters.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ruan.cesar.adapters.auth.HashService;
import org.ruan.cesar.adapters.persistence.UserJpaEntity;
import org.ruan.cesar.aplication.AuthRepository;
import org.ruan.cesar.domain.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

@ApplicationScoped
public class PostgresAuthRepository implements AuthRepository {

    private final EntityManager entityManager;

    public PostgresAuthRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public byte[] hashPassword(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return HashService.hash(password);
    }

    public Boolean checkUser(String username, byte[] hashedPassword) {
        var user = entityManager.find(UserJpaEntity.class, username);
        if (Arrays.equals(user.getPassword(), hashedPassword) && user.getUser().equals(username)) return true;
        else return false;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        if (user.getUsername().isBlank() || Arrays.toString(user.getPassword()).isBlank()) return; // criar um erro;
        var userJpa = new UserJpaEntity(user.getRole(), user.getUsername(), user.getPassword());
        entityManager.persist(userJpa);
    }
}
