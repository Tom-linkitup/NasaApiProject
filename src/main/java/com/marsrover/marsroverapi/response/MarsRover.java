package com.marsrover.marsroverapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarsRover {

    private Long id;
    private String name;

    @JsonProperty("landing_date")
    private String landingDate;

    @JsonProperty("launch_date")
    private String launchDate;

    @Override
    public String toString() {
        return "MarsRover{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", landingDate=" + landingDate +
                '}';
    }
}
