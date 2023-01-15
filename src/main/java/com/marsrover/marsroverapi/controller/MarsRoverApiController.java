package com.marsrover.marsroverapi.controller;

import com.marsrover.marsroverapi.dto.MarsRoverApiDto;
import com.marsrover.marsroverapi.response.MarsRoverApiResponse;
import com.marsrover.marsroverapi.service.MarsRoverApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.InvocationTargetException;

@Controller
public class MarsRoverApiController {

    private static final String DEFAULT_ROVER = "Opportunity";
    private static final Integer DEFAULT_SOL = 1;

    private MarsRoverApiService marsRoverApiService;

    @Autowired
    public MarsRoverApiController(MarsRoverApiService marsRoverApiService) {
        this.marsRoverApiService = marsRoverApiService;
    }

    @GetMapping("/")
    public String homepageView(ModelMap model) throws InvocationTargetException, IllegalAccessException {

        MarsRoverApiDto defaultDto = MarsRoverApiDto.builder()
                .marsSol(DEFAULT_SOL)
                .roverName(DEFAULT_ROVER)
                .build();

        MarsRoverApiResponse roverData = marsRoverApiService.getMarsRoverData(defaultDto);
        model.put("roverData", roverData);
        model.put("defaultDto", defaultDto);
        model.put("cameras", marsRoverApiService.getCamerasOnRover().get(defaultDto.getRoverName()));

        return "homepage";
    }
}
