// src/test/java/com/example/app/domain/FeePolicyTest.java
package com.example.app.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FeePolicyTest {

    @Test
    void fx_1000000_donne_100_00() {
        BigDecimal fee = FeePolicy.feeFor(new Fx("EUR/USD"), BigDecimal.valueOf(1_000_000));
        assertThat(fee).isEqualByComparingTo("100.00");
    }

    @Test
    void obligation_3_ans_200000_donne_100_00() {
        BigDecimal fee = FeePolicy.feeFor(new Bond("US1234567890", 3), BigDecimal.valueOf(200_000));
        assertThat(fee).isEqualByComparingTo("100.00");
    }

    @Test
    void obligation_10_ans_200000_donne_200_00() {
        BigDecimal fee = FeePolicy.feeFor(new Bond("US1234567890", 10), BigDecimal.valueOf(200_000));
        assertThat(fee).isEqualByComparingTo("200.00");
    }

    @Test
    void obligation_5_ans_200000_donne_100_00() {
        BigDecimal fee = FeePolicy.feeFor(new Bond("US1234567890", 5), BigDecimal.valueOf(200_000));
        assertThat(fee).isEqualByComparingTo("100.00");
    }

    @Test
    void action_1000_donne_5_00_minimum() {
        BigDecimal fee = FeePolicy.feeFor(new Equity("AAPL"), BigDecimal.valueOf(1_000));
        assertThat(fee).isEqualByComparingTo("5.00");
    }

    @Test
    void action_10000_donne_20_00() {
        BigDecimal fee = FeePolicy.feeFor(new Equity("AAPL"), BigDecimal.valueOf(10_000));
        assertThat(fee).isEqualByComparingTo("20.00");
    }

    @Test
    void montant_negatif_leve_InvalidTradeException() {
        assertThatThrownBy(() -> FeePolicy.feeFor(new Fx("EUR/USD"), BigDecimal.valueOf(-1)))
                .isInstanceOf(InvalidTradeException.class)
                .hasMessage("Amount must be greater than zero.");
    }
}