package org.ruan.cesar.domain;

import org.ruan.cesar.domain.enums.Status;
import org.ruan.cesar.aplication.exceptions.AccessPointOfflineException;

public class AccessPoint {

    private Status status;
    private final String macAddress;
    private String apName;
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

    public String getMacAddress() {
        return macAddress;
    }
    public Status getStatus(){
        return status;
    }


}

