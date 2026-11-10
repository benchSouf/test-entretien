// src/main/java/com/example/app/domain/InvalidTradeException.java
package com.example.app.domain;

public class InvalidTradeException extends RuntimeException {
    public InvalidTradeException(String message) {
        super(message);
    }
}