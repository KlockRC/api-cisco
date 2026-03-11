package org.ruan.cesar.domain;

import lombok.Getter;
import org.ruan.cesar.domain.enums.Status;

@Getter
public class AccessPoint {

    private final Long userId;
    private final Status status;
    private final String macAddress;
    private final String apName;
    private String firmwareVersion;

    public AccessPoint(Long userId,Status status, String macAddress, String apName, String firmwareVersion) {
        this.userId = userId;
        this.status = status;
        this.macAddress = macAddress;
        this.apName = apName;
        this.firmwareVersion = firmwareVersion;
    }

    public void upgradeFirmwareVersion(String firmwareVersion) {
            this.firmwareVersion = firmwareVersion;
    }


}

