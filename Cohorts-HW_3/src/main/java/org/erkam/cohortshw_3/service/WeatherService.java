package org.erkam.cohortshw_3.service;

import org.erkam.cohortshw_3.model.OpenWeatherMapResponse;
import org.erkam.cohortshw_3.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeather(String lat, String lon) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();

        OpenWeatherMapResponse response = restTemplate.getForObject(url, OpenWeatherMapResponse.class);

        WeatherResponse weatherResponse = new WeatherResponse();
        if (response != null) {
            weatherResponse.setCity(response.getName());
            weatherResponse.setCountry(response.getSys().getCountry());
            weatherResponse.setDescription(response.getWeather().get(0).getDescription());
            weatherResponse.setTemperature(response.getMain().getTemp());
        }

        return weatherResponse;
    }
}
