package org.ruan.cesar.aplication.exceptions;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.util.Map;

public class AccessPointExceptionHandler {

    @ServerExceptionMapper
    public Response handleNotFound(AccessPointNotFoundException e){
        return Response.status(Response.Status.NOT_FOUND)
                .entity(Map.of("error", e.getMessage()))
                .build();
    }
    @ServerExceptionMapper
    public Response handeIsOffline(AccessPointOfflineException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("error", e.getMessage()))
                .build();
    }

}
