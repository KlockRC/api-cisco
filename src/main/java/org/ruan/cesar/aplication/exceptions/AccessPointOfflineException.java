package org.ruan.cesar.aplication.exceptions;

public class AccessPointOfflineException extends RuntimeException {
    public AccessPointOfflineException(){super("api.AcessPoint is offline| can't complete the update of firmware");}
}
