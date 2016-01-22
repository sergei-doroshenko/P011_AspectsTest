package org.sergei.business.service;


import org.sergei.business.exception.BusinessException;

import java.util.List;

public interface PriceService {

    void validatePrices(List<Integer> prices) throws BusinessException;

    double calculateAvgPrice(List<Integer> prices);
}
