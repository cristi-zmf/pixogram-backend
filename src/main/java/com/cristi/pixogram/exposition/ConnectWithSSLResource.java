package com.cristi.pixogram.exposition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class ConnectWithSSLResource {

    private final RestTemplate restClient;

    public ConnectWithSSLResource(@Autowired @Qualifier("noSsl") RestTemplate restClient) {
        this.restClient = restClient;
    }

    @GetMapping(value = "connect-to-ssl", produces = MediaType.TEXT_PLAIN_VALUE)
    public String connectToSslWithRestTemplate() {
        return restClient.getForObject("https://localhost:8081/api/protected-string", String.class);
    }
}
