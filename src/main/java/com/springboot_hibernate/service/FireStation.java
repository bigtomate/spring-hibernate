package com.springboot_hibernate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springboot_hibernate.dto.HolidayDTO;
import com.springboot_hibernate.entity.Holiday;
import com.springboot_hibernate.exception.HolidayNotFoundException;
import com.springboot_hibernate.repository.HolidayRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FireStation {

    private final RestClient.Builder restClientBuilder;
    private final RestClient restClient;

    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    HolidayRepository holidayRepository;

    public FireStation(RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
        this.restClient = createRestClient("https://www.gov.uk/bank-holidays.json", "");
    }

/*  public HolidayDTO retrieveHolidaysByRegion(String region) {
        HolidayDTO holidayDTO =   restClient.get()
                .uri("https://www.gov.uk/bank-holidays.json")
                .retrieve()
                .body(HolidayDTO.class);
        System.out.println(holidayDTO.toString());
        return holidayDTO;
    }*/

    public HolidayDTO retrieveHolidaysByRegion(String region) throws IOException, HolidayNotFoundException {
        /*URI uri = new URI("https://www.gov.uk/bank-holidays.json");
        URL url = uri.toURL();*/
        File file =  resourceLoader.getResource(
                "classpath:service/holiday.json").getFile();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        Map result = mapper.readValue(file, HashMap.class);
        var filtedResult = result.get(region);
        if (filtedResult == null) {
            throw new HolidayNotFoundException(String.format("no holiday found of the given region %s", region));
        }
        final HolidayDTO holiday = new ObjectMapper().convertValue(filtedResult, HolidayDTO.class);
        List<Holiday> entities = holiday.getEvents().stream().map(eventDTO -> {
            Holiday holidayEntity = Holiday.builder().division(holiday.getDivision()).build();
            BeanUtils.copyProperties(eventDTO, holidayEntity);
            return holidayEntity;
        }).toList();
        holidayRepository.saveAll(entities);
        return holiday;
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
