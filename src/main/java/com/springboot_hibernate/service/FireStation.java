package com.springboot_hibernate.service;

import com.springboot_hibernate.dto.HolidayDTO;
import com.springboot_hibernate.hackerrank_restapi.FootBallApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class FireStation {

    private final RestClient.Builder restClientBuilder;
    private final RestClient restClient;
    public FireStation(RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
        this.restClient = createRestClient("https://www.gov.uk/bank-holidays.json", "");
    }

    public HolidayDTO retrieveHolidaysByRegion(String region) {
        HolidayDTO holidayDTO =   restClient.get()
                .uri("https://www.gov.uk/bank-holidays.json")
                .retrieve()
                .body(HolidayDTO.class);
    System.out.println(holidayDTO.toString());
    return holidayDTO;
    }

    private RestClient createRestClient(String baseUrl, String bearerToken) {
        return restClientBuilder
                .baseUrl(baseUrl)
                .requestInterceptor((request, body, execution) -> {
                    if (bearerToken != null && !bearerToken.isEmpty()) {
                        request.getHeaders().setBearerAuth(bearerToken);
                    }
                    return execution.execute(request, body);
                })
                .build();
    }
}
