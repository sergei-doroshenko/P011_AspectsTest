package org.sergei.business.service.impl;

import org.sergei.business.exception.BusinessException;
import org.sergei.business.exception.ImportantException;
import org.sergei.business.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.IntSummaryStatistics;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Override
    public void validatePrices(List<Integer> prices) throws BusinessException {
        if (prices == null) {
            throw new IllegalArgumentException("Prices are null");
        }
        IntSummaryStatistics stats = getIntSummaryStatistics(prices);
        long sum = stats.getSum();
        if (sum > 100) {
            throw new ImportantException("Prices are too high");
        }
    }

    private IntSummaryStatistics getIntSummaryStatistics(List<Integer> prices) {
        return prices.stream().mapToInt((x) -> x).summaryStatistics();
    }

    @Override
    public final double calculateAvgPrice(List<Integer> prices) {
        IntSummaryStatistics stats = getIntSummaryStatistics(prices);
        return stats.getAverage();
    }
}
