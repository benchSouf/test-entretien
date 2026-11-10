// src/main/java/com/example/app/application/PricedTrade.java
package com.example.app.application;

import com.example.app.domain.Product;

import java.math.BigDecimal;

public record PricedTrade(Product product, BigDecimal amount) {
}