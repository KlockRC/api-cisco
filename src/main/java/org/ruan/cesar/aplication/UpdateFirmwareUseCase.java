package org.ruan.cesar.aplication;

import jakarta.enterprise.context.ApplicationScoped;
import org.ruan.cesar.aplication.exceptions.AccessPointNotFoundException;
import org.ruan.cesar.aplication.exceptions.AccessPointOfflineException;
import org.ruan.cesar.domain.enums.Status;

@ApplicationScoped
public class UpdateFirmwareUseCase {

    private final AccessPointRepository repository;

    public UpdateFirmwareUseCase (AccessPointRepository repository){
        this.repository =  repository;
    }

    public void updateFirmware(Long userId,String macAddress, String newVersion) {
        var accessPoint = this.repository.findAccessPoint(macAddress, userId);
        if (accessPoint == null) throw new AccessPointNotFoundException();
        if (accessPoint.getStatus() != Status.Online) throw new AccessPointOfflineException();
        accessPoint.upgradeFirmwareVersion(newVersion);
        this.repository.saveAccessPoint(accessPoint);
    }

}
