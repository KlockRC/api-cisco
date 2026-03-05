package org.ruan.cesar.aplication.exceptions;

public class AccessPointNotFoundException extends RuntimeException {
    public AccessPointNotFoundException(){super("AccessPoint Not Found | Pls Check The MacAddress");}
}
