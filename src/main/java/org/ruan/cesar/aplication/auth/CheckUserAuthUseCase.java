package org.ruan.cesar.aplication.auth;

import jakarta.enterprise.context.ApplicationScoped;
import org.ruan.cesar.aplication.AuthRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@ApplicationScoped
public class CheckUserAuthUseCase {

    private AuthRepository repository;

    public CheckUserAuthUseCase(AuthRepository repository){
        this.repository = repository;
    }

    public Boolean checkUser(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        var hashPassword = this.repository.hashPassword(password);
        return this.repository.checkUser(username, hashPassword);
    }
}
