package org.ruan.cesar.domain;

import lombok.Getter;
import org.ruan.cesar.domain.enums.Status;

@Getter
public class AccessPoint {

    private final Status status;
    private final String macAddress;
    private final String apName;
    private String firmwareVersion;

    public AccessPoint(Status status, String macAddress, String apName, String firmwareVersion) {
        this.status = status;
        this.macAddress = macAddress;
        this.apName = apName;
        this.firmwareVersion = firmwareVersion;
    }

    public void upgradeFirmwareVersion(String firmwareVersion) {
            this.firmwareVersion = firmwareVersion;
    }


}

