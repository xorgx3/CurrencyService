package pl.luks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRatesTable {
    private Collection<Rate> rates;

    public Collection<Rate> getRates() {
        return rates;
    }

    public void setRates(Collection<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRatesTable that = (ExchangeRatesTable) o;
        return Objects.equals(rates, that.rates);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rates);
    }

    @Override
    public String toString() {
        return "ExchangeRatesTable{" +
                "rates=" + rates +
                '}';
    }
}
