// src/test/java/com/example/app/application/TotalFeesUseCaseTest.java
package com.example.app.application;

import com.example.app.domain.FeePolicy;
import com.example.app.domain.Fx;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TotalFeesUseCaseTest {

    @Test
    void cinq_trades_fx_1000000_feesPerBatch_retourne_trois_lots() {
        TotalFeesUseCase useCase = new TotalFeesUseCase();
        List<PricedTrade> trades = List.of(
                new PricedTrade(new Fx("EUR/USD"), BigDecimal.valueOf(1_000_000)),
                new PricedTrade(new Fx("EUR/USD"), BigDecimal.valueOf(1_000_000)),
                new PricedTrade(new Fx("EUR/USD"), BigDecimal.valueOf(1_000_000)),
                new PricedTrade(new Fx("EUR/USD"), BigDecimal.valueOf(1_000_000)),
                new PricedTrade(new Fx("EUR/USD"), BigDecimal.valueOf(1_000_000))
        );

        List<BigDecimal> fees = useCase.feesPerBatch(trades, 2);

        assertThat(fees.stream().map(BigDecimal::toPlainString))
                .containsExactly("200.00", "200.00", "100.00");
    }
}