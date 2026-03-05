package org.ruan.cesar.aplication;

import org.ruan.cesar.domain.AccessPoint;

public interface AccessPointRepository {
    AccessPoint findAccessPoint(String macAddress);
    void saveAccessPoint(AccessPoint accessPoint);
}
