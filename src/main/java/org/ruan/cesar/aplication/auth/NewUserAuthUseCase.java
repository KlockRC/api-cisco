package org.ruan.cesar.aplication.auth;

import jakarta.enterprise.context.ApplicationScoped;
import org.ruan.cesar.aplication.AuthRepository;
import org.ruan.cesar.domain.User;
import org.ruan.cesar.domain.enums.Roles;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@ApplicationScoped
public class NewUserAuthUseCase {

    private final AuthRepository repository;

    public NewUserAuthUseCase(AuthRepository repository){
        this.repository = repository;
    }

    public void newUser(String username, String password, String role) throws InvalidKeySpecException, UnsupportedEncodingException, NoSuchAlgorithmException {
        var hashPassword = this.repository.hashPassword(password);
        var user = new User(null ,Roles.valueOf(role), username, hashPassword);
        this.repository.saveUser(user);
    }
}
