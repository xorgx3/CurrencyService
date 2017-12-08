package pl.luks.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.luks.dto.Rate;
import pl.luks.exception.CurrencyNotFoundException;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class CurrencyService {

    @Autowired
    private Set<Rate> rates;

    public BigDecimal calculateCurrency(BigDecimal amount, String currency, String targetCurrency) {
        BigDecimal midCurrency = findMid(currency);
        BigDecimal midTargetCurrency = findMid(targetCurrency);

        return amount.multiply(midCurrency.divide(midTargetCurrency, BigDecimal.ROUND_HALF_UP));

    }

    private BigDecimal findMid(String currencyCode) {
        return rates.stream()
                .filter(rate -> StringUtils.equalsIgnoreCase(rate.getCode(), currencyCode))
                .findFirst().orElseThrow(CurrencyNotFoundException::new)
                .getMid();
    }


    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }
}
