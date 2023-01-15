package com.marsrover.marsroverapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarsRoverApiDto {

    private String roverName;
    private Integer marsSol;
    private Boolean cameraFhaz;
    private Boolean cameraRhaz;
    private Boolean cameraMast;
    private Boolean cameraChemcam;
    private Boolean cameraMahli;
    private Boolean cameraMardi;
    private Boolean cameraNavcam;
    private Boolean cameraPancam;
    private Boolean cameraMinites;

    @Override
    public String toString() {
        return "MarsRoverApiDto{" +
                "roverName='" + roverName + '\'' +
                ", marsSol=" + marsSol +
                ", cameraFhaz=" + cameraFhaz +
                ", cameraRhaz=" + cameraRhaz +
                ", cameraMast=" + cameraMast +
                ", cameraChemcam=" + cameraChemcam +
                ", cameraMahli=" + cameraMahli +
                ", cameraMardi=" + cameraMardi +
                ", cameraNavcam=" + cameraNavcam +
                ", cameraPancam=" + cameraPancam +
                ", cameraMinites=" + cameraMinites +
                '}';
    }
}
