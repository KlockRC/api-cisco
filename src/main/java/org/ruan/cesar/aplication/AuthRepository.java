package org.ruan.cesar.aplication;

import org.ruan.cesar.domain.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthRepository {

    byte[] hashPassword(String password) throws InvalidKeySpecException, NoSuchAlgorithmException;
    Boolean checkUser(String username, byte[] hashedPassword);
    void saveUser(User user);

}
