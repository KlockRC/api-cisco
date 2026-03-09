package org.ruan.cesar.adapters.auth;

import jakarta.enterprise.context.ApplicationScoped;
import io.smallrye.jwt.build.Jwt;

import java.time.Duration;
import java.util.Set;

@ApplicationScoped
public class JwtService {

    public String generateToken(String username, Set<String> role){
        return Jwt.issuer("api-cisco").upn(username).groups(role).expiresIn(Duration.ofDays(1)).sign();
    }

}
