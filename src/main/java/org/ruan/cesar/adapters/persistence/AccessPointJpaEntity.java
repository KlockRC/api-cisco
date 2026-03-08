package org.ruan.cesar.adapters.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ruan.cesar.domain.enums.Status;
import static jakarta.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "access_point")
public class AccessPointJpaEntity {

    @Enumerated(STRING)
    private Status status;
    @Id
    private String macAddress;
    private String apName;
    private String firmwareVersion;
}
