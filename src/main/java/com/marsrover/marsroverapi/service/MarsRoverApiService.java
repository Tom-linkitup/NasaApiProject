package com.marsrover.marsroverapi.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarsRoverApiService {

    private static final String API_KEY = "SX075VOQVamYL6As11rgSnR12keoS2aOjhRy5GJN";

    private Map<String, List<String>> camerasOnRover = new HashMap<>();

    public MarsRoverApiService() {
        camerasOnRover.put("Opportunity", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
        camerasOnRover.put("Curiosity", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "NAVCAM", "MARDI"));
        camerasOnRover.put("Spirit", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
    }

    public Map<String, List<String>> getCamerasOnRover() {
        return camerasOnRover;
    }
}
