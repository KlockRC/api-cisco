package org.ruan.cesar.adapters.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.ruan.cesar.adapters.persistence.AccessPointJpaEntity;
import org.ruan.cesar.adapters.persistence.UserJpaEntity;
import org.ruan.cesar.domain.AccessPoint;

@ApplicationScoped
public class AccessPointMapper {

    public static AccessPointJpaEntity toEntity(AccessPoint accessPoint){
        var entity = new AccessPointJpaEntity();
        entity.setApName(accessPoint.getApName());
        entity.setStatus(accessPoint.getStatus());
        entity.setMacAddress(accessPoint.getMacAddress());
        entity.setFirmwareVersion(accessPoint.getFirmwareVersion());
        var user = new UserJpaEntity();
        user.setId(accessPoint.getUserId());
        entity.setUserId(user);
        return entity;
    }

    public static AccessPoint toDomain(AccessPointJpaEntity entity){
        return new AccessPoint(
                entity.getUserId().getId(),
                entity.getStatus(),
                entity.getMacAddress(),
                entity.getApName(),
                entity.getFirmwareVersion()
        );
    }
}
