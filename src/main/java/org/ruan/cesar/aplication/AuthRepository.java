package org.ruan.cesar.aplication;

import org.ruan.cesar.domain.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthRepository {

    String hashPassword(String password) throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException;
    Boolean checkUser(String username, String hashedPassword) throws NoSuchAlgorithmException;
    void saveUser(User user);

}
