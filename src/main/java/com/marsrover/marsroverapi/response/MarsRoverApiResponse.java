package com.marsrover.marsroverapi.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarsRoverApiResponse {

    private List<MarsPhoto> photos = new ArrayList<>();

    @Override
    public String toString() {
        return "MarsRoverApiResponse{" +
                "photos=" + photos +
                '}';
    }
}
