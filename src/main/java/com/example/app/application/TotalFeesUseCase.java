package com.example.app.application;

import com.example.app.domain.FeePolicy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TotalFeesUseCase {

    public List<BigDecimal> feesPerBatch(List<PricedTrade> trades, int batchSize) {
        List<BigDecimal> batchFees = new ArrayList<>();
        for (int i = 0; i < trades.size(); i += batchSize) {
            BigDecimal batchFee = trades.subList(i, Math.min(i + batchSize, trades.size())).stream()
                    .map(trade -> FeePolicy.feeFor(trade.product(), trade.amount()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            batchFees.add(batchFee);
        }
        return batchFees;
    }
}