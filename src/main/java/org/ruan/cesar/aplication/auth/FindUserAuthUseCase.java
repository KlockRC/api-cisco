package org.ruan.cesar.aplication.auth;

import jakarta.enterprise.context.ApplicationScoped;
import org.ruan.cesar.aplication.AuthRepository;
import org.ruan.cesar.domain.User;

@ApplicationScoped
public class FindUserAuthUseCase {
    private AuthRepository repository;

    public FindUserAuthUseCase(AuthRepository repository){
        this.repository = repository;
    }

    public User findUser(String username){
        return this.repository.findByUsername(username);
    }
}
