package pl.luks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.luks.dto.Rate;
import pl.luks.service.CurrencyService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurrencyServiceTest {

    private Set<Rate> rates;
    @Autowired
    private CurrencyService currencyService;

    private BigDecimal amount;
    private String currency;
    private String targetCurrency;


    @Before
    public void setup() {
        amount = new BigDecimal("100");
        currency = "EUR";
        targetCurrency = "USD";
        rates = new HashSet<>(Arrays.asList(new Rate("dolar amerykański", "USD", new BigDecimal("3.5653")),
                new Rate("euro", "EUR", new BigDecimal("4.2154"))));
        currencyService.setRates(rates);
    }

    @Test
    public void ServiceInitializedCorrectly() {
        assertThat(currencyService).isNotNull();
    }

    @Test
    public void calculateCurrencyTest() {
        BigDecimal bigDecimal = currencyService.calculateCurrency(amount, currency, targetCurrency);
        assertThat(bigDecimal).isEqualTo("118.2300");
    }


}
