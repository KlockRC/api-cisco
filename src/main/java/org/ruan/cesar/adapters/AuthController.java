package org.ruan.cesar.adapters;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.ruan.cesar.adapters.auth.JwtService;
import org.ruan.cesar.adapters.dto.LoginRequest;
import org.ruan.cesar.adapters.dto.NewUserRequest;
import org.ruan.cesar.aplication.auth.CheckUserAuthUseCase;
import org.ruan.cesar.aplication.auth.NewUserAuthUseCase;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

@PermitAll
@Path("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final NewUserAuthUseCase newUserAuthUseCase;
    private final CheckUserAuthUseCase checkUserAuthUseCase;

    public AuthController(JwtService jwtService,CheckUserAuthUseCase checkUserAuthUseCase, NewUserAuthUseCase newUserAuthUseCase) {
        this.checkUserAuthUseCase = checkUserAuthUseCase;
        this.newUserAuthUseCase = newUserAuthUseCase;
        this.jwtService = jwtService;
    }

    @Path("/login")
    @POST
    public Response login(LoginRequest request) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (this.checkUserAuthUseCase.checkUser(request.username(), request.password())) {
            var token = this.jwtService.generateToken(request.username(), Set.of("Admin"));
            return Response.ok(token).build();
        }
        else return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @Path("/register")
    @POST
    public void register(NewUserRequest request) throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        this.newUserAuthUseCase.newUser(request.username(), request.password(), request.role());
    }


}
