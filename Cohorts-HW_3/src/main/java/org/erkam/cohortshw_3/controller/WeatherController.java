package org.erkam.cohortshw_3.controller;

import lombok.RequiredArgsConstructor;
import org.erkam.cohortshw_3.model.WeatherRequest;
import org.erkam.cohortshw_3.model.WeatherResponse;
import org.erkam.cohortshw_3.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @PostMapping
    public ResponseEntity<WeatherResponse> getWeather(@RequestBody WeatherRequest request) {
        WeatherResponse response = weatherService.getWeather(request.getLatitude(), request.getLongitude());
        return ResponseEntity.ok(response);
    }
}
