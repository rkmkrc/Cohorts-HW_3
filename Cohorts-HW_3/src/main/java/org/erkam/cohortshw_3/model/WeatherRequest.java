package org.erkam.cohortshw_3.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRequest {
    private String latitude;
    private String longitude;
}
