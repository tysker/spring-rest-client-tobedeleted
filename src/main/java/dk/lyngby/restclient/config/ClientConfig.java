package dk.lyngby.restclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.time.Duration;

import org.springframework.http.client.SimpleClientHttpRequestFactory;


@Configuration
public class ClientConfig {

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        // Timeouts are still configured on the underlying HTTP client library factory
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout((int) Duration.ofSeconds(2).toMillis());
        requestFactory.setReadTimeout((int) Duration.ofSeconds(5).toMillis());

        return builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .requestFactory(requestFactory)
                .build();
    }
}

