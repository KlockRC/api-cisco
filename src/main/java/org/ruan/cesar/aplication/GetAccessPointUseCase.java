package org.ruan.cesar.aplication;

import jakarta.enterprise.context.ApplicationScoped;
import org.ruan.cesar.aplication.exceptions.AccessPointNotFoundException;
import org.ruan.cesar.domain.AccessPoint;

@ApplicationScoped
public class GetAccessPointUseCase {

    private final AccessPointRepository repository;

    public GetAccessPointUseCase(AccessPointRepository repository){
        this.repository = repository;
    }

    public AccessPoint getAccessPoint(String macAddress, Long userId) {
        var accessPoint = this.repository.findAccessPoint(macAddress, userId);
        if (accessPoint == null) throw new AccessPointNotFoundException();
        return accessPoint;
    }

}
