package org.ruan.cesar.adapters.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.ruan.cesar.domain.enums.Status;
import static jakarta.persistence.EnumType.STRING;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "access_point")
public class AccessPointJpaEntity {


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity userId;
    @Enumerated(STRING)
    private Status status;
    @Id
    private String macAddress;
    private String apName;
    private String firmwareVersion;
}
