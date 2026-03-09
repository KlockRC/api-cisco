package org.ruan.cesar.adapters;

import io.quarkus.security.Authenticated;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.ruan.cesar.adapters.dto.AccessPointResponse;
import org.ruan.cesar.adapters.dto.NewAccessPointRequest;
import org.ruan.cesar.aplication.GetAccessPointUseCase;
import org.ruan.cesar.aplication.NewAccessPointUseCase;
import org.ruan.cesar.aplication.UpdateFirmwareUseCase;
import org.ruan.cesar.domain.enums.Status;

@Authenticated
@Path("/access-point")
public class AccessPointController {
    private final UpdateFirmwareUseCase updateFirmwareUseCase;
    private final NewAccessPointUseCase newAccessPointUseCase;
    private final GetAccessPointUseCase getAccessPointUseCase;

    public AccessPointController (UpdateFirmwareUseCase updateFirmwareUseCase, NewAccessPointUseCase newAccessPointUseCase, GetAccessPointUseCase getAccessPointUseCase) {
        this.updateFirmwareUseCase = updateFirmwareUseCase;
        this.newAccessPointUseCase = newAccessPointUseCase;
        this.getAccessPointUseCase = getAccessPointUseCase;
    }
    @Path("/{macAddress}/firmware")
    @PATCH
    public void updateFirmware(@PathParam("macAddress") String macAddress, String newVersion) {
        this.updateFirmwareUseCase.updateFirmware(macAddress, newVersion);
    }

    @Path("/new")
    @POST
    public void newAccessPoint(NewAccessPointRequest request) {
        this.newAccessPointUseCase.newAccessPoint(
                Status.valueOf(request.status()),
                request.macAddress(),
                request.apName(),
                request.firmwareVersion()
        );
    }

    @Path("/{macAddress}")
    @GET
    public Response getAccessPoint(@PathParam("macAddress") String macAddress) {
        var ap = this.getAccessPointUseCase.getAccessPoint(macAddress);
        return Response.ok(new AccessPointResponse(ap.getStatus().name(), ap.getMacAddress(), ap.getApName(), ap.getFirmwareVersion())).build();

    }
}
