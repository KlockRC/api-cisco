package org.ruan.cesar.adapters.auth;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.elytron.security.common.BcryptUtil;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@ApplicationScoped
public class HashService {

    @Inject
    @ConfigProperty(name = "hash.salt")
    String pepperString;

    public  String hash(String password) throws NoSuchAlgorithmException {
        var hash = MessageDigest.getInstance("SHA-256").digest(password.concat(pepperString).getBytes(StandardCharsets.UTF_8));
        return BcryptUtil.bcryptHash(HexFormat.of().formatHex(hash));
    }

    public Boolean compareHash(String password, String storagePassword) throws NoSuchAlgorithmException {
        var hash = MessageDigest.getInstance("SHA-256").digest(password.concat(pepperString).getBytes(StandardCharsets.UTF_8));
        return BcryptUtil.matches(HexFormat.of().formatHex(hash),storagePassword);
    }
}
