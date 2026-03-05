package org.ruan.cesar.adapters;

import jakarta.enterprise.context.ApplicationScoped;
import org.ruan.cesar.aplication.AccessPointRepository;
import org.ruan.cesar.domain.AccessPoint;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class InMemoryAccessPointRepository implements AccessPointRepository {

    private final Map<String, AccessPoint> database = new HashMap<>();

    public AccessPoint findAccessPoint(String macAddress) {
        System.out.println("Searching for access point with mac address: " + macAddress);
        return database.get(macAddress);
    }

    public void saveAccessPoint(AccessPoint accessPoint) {
        System.out.println("Saving access point: " + accessPoint);
        database.put(accessPoint.getMacAddress(), accessPoint);
    }
}
