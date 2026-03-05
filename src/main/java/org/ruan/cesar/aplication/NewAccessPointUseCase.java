package org.ruan.cesar.aplication;

import jakarta.enterprise.context.ApplicationScoped;
import org.ruan.cesar.domain.AccessPoint;
import org.ruan.cesar.domain.enums.Status;

@ApplicationScoped
public class NewAccessPointUseCase {

    private final AccessPointRepository repository;

    public NewAccessPointUseCase(AccessPointRepository repository){
        this.repository = repository;
    }

    public void newAccessPoint (Status status, String macAddress, String apName, String firmwareVersion) {
        var accessPoint = new AccessPoint(status, macAddress, apName, firmwareVersion);
        this.repository.saveAccessPoint(accessPoint);
    }
}
