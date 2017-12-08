package pl.luks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import pl.luks.dto.Rate;

import java.util.HashSet;
import java.util.Set;

@org.springframework.context.annotation.Configuration
@EnableScheduling
public class Configuration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Set<Rate> rates(){
        return new HashSet<>();
    }
}
