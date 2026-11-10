// src/main/java/com/example/app/domain/FeePolicy.java
package com.example.app.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class FeePolicy {

    private FeePolicy() {
        // Private constructor to prevent instantiation
    }

    public static BigDecimal feeFor(Product product, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTradeException("Amount must be greater than zero.");
        }

        BigDecimal fee = switch (product) {
            case Fx fx -> amount.multiply(BigDecimal.valueOf(0.0001));
            case Bond bond -> bond.maturityYears() <= 5
                    ? amount.multiply(BigDecimal.valueOf(0.0005))
                    : amount.multiply(BigDecimal.valueOf(0.001));
            case Equity equity -> {
                BigDecimal calculatedFee = amount.multiply(BigDecimal.valueOf(0.002));
                yield calculatedFee.max(BigDecimal.valueOf(5));
            }
        };

        return fee.setScale(2, RoundingMode.HALF_UP);
    }
}