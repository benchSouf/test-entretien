// src/main/java/com/example/app/domain/Bond.java
package com.example.app.domain;

public record Bond(String isin, int maturityYears) implements Product {
}