package org.ruan.cesar.adapters.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.ruan.cesar.adapters.mapper.AccessPointMapper;
import org.ruan.cesar.adapters.persistence.AccessPointJpaEntity;
import org.ruan.cesar.aplication.AccessPointRepository;
import org.ruan.cesar.domain.AccessPoint;

import java.util.Objects;

@ApplicationScoped
public class PostgresAccessPointRepository  implements AccessPointRepository {

    private final EntityManager entityManager;

    public PostgresAccessPointRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public AccessPoint findAccessPoint(String macAddress, Long userId) {
        System.out.println("Searching for access point with mac address: " + macAddress);
        var ap = entityManager.find(AccessPointJpaEntity.class, macAddress);
        if (ap == null || !Objects.equals(ap.getUserId().getId(), userId)) return null;
        return AccessPointMapper.toDomain(ap);
    }

    @Transactional
    @Override
    public void saveAccessPoint(AccessPoint accessPoint) {
        System.out.println("Saving access point: " + accessPoint);
        entityManager.merge(AccessPointMapper.toEntity(accessPoint));
    }
}
