package org.ruan.cesar.adapters.auth;

import jakarta.enterprise.context.ApplicationScoped;
import io.smallrye.jwt.build.Jwt;

import java.time.Duration;
import java.util.Set;

@ApplicationScoped
public class JwtService {

    public String generateToken(Long userId, Set<String> role){
        return Jwt.issuer("api-cisco").upn(userId.toString()).groups(role).expiresIn(Duration.ofDays(1)).sign();
    }

}
