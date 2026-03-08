package org.ruan.cesar.adapters.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ruan.cesar.adapters.persistence.AccessPointJpaEntity;
import org.ruan.cesar.aplication.AccessPointRepository;
import org.ruan.cesar.domain.AccessPoint;

@ApplicationScoped
public class PostgresAccessPointRepository  implements AccessPointRepository {

    private final EntityManager entityManager;

    public PostgresAccessPointRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public AccessPoint findAccessPoint(String macAddress) {
        System.out.println("Searching for access point with mac address: " + macAddress);
        var ap = entityManager.find(AccessPointJpaEntity.class, macAddress);
        if (ap == null) return null;
        return new AccessPoint(ap.getStatus(), ap.getMacAddress(), ap.getApName(), ap.getFirmwareVersion());
    }

    @Transactional
    @Override
    public void saveAccessPoint(AccessPoint accessPoint) {
        System.out.println("Saving access point: " + accessPoint);
        var ap = new AccessPointJpaEntity(accessPoint.getStatus(), accessPoint.getMacAddress(), accessPoint.getApName(), accessPoint.getFirmwareVersion());
        entityManager.merge(ap);
    }
}
