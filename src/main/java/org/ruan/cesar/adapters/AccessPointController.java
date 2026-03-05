package org.ruan.cesar.adapters;

import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.ruan.cesar.adapters.dto.NewAccessPointRequest;
import org.ruan.cesar.aplication.NewAccessPointUseCase;
import org.ruan.cesar.aplication.UpdateFirmwareUseCase;
import org.ruan.cesar.domain.enums.Status;


@Path("/access-point")
public class AccessPointController {
    private final UpdateFirmwareUseCase updateFirmwareUseCase;
    private final NewAccessPointUseCase newAccessPointUseCase;

    public AccessPointController (UpdateFirmwareUseCase updateFirmwareUseCase, NewAccessPointUseCase newAccessPointUseCase) {
        this.updateFirmwareUseCase = updateFirmwareUseCase;
        this.newAccessPointUseCase = newAccessPointUseCase;
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
}
