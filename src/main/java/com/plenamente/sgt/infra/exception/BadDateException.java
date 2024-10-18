package com.plenamente.sgt.infra.exception;

public class BadDateException extends RuntimeException {
    public BadDateException(String message) {
        super(message);
    }
}
