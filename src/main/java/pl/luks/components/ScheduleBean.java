package pl.luks.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.luks.dto.ExchangeRatesTable;
import pl.luks.dto.Rate;

import java.util.Arrays;
import java.util.Set;

@Component
public class ScheduleBean {

    private static final int MILISECONDS_IN_DAY = 86_400_000;

    private static final String A = "a";
    private static final String B = "b";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Set<Rate> rates;

    @Value("${operations.restURL}")
    private String serviceURL;

    @Scheduled(fixedRate = MILISECONDS_IN_DAY)
    public void run() {
        ExchangeRatesTable[] tableA = restTemplate.getForObject(getUrl(A), ExchangeRatesTable[].class);
        ExchangeRatesTable[] tableB = restTemplate.getForObject(getUrl(B), ExchangeRatesTable[].class);

        for (ExchangeRatesTable[] exchangeRatesTable : Arrays.asList(tableA, tableB)) {
            for (ExchangeRatesTable ratesTable : exchangeRatesTable) {
                rates.addAll(ratesTable.getRates());
            }
        }
    }

    private String getUrl(String arg) {
        return serviceURL + "/" + arg;
    }
}
