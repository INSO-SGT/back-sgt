package com.plenamente.sgt.infra.exception;

public class BadDurationException extends RuntimeException {
    public BadDurationException(String message) {
        super(message);
    }
}
