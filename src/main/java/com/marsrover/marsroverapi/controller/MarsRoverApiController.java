package com.marsrover.marsroverapi.controller;

import com.marsrover.marsroverapi.dto.MarsRoverApiDto;
import com.marsrover.marsroverapi.response.MarsRoverApiResponse;
import com.marsrover.marsroverapi.service.MarsRoverApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

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
    public String homepageView() {
        return "redirect:/" + DEFAULT_ROVER;
    }

    @GetMapping("/{roverName}")
    public String changeRoverView(@PathVariable String roverName, ModelMap model) throws InvocationTargetException, IllegalAccessException {

        MarsRoverApiDto defaultDto = MarsRoverApiDto.builder()
                .marsSol(DEFAULT_SOL)
                .roverName(roverName)
                .build();
        MarsRoverApiResponse roverData = marsRoverApiService.getMarsRoverData(defaultDto);
        model.put("roverData", roverData);
        model.put("defaultDto", defaultDto);
        List<String> cameras = marsRoverApiService.getCamerasOnRover().get(roverName);
        if (Objects.isNull(cameras)) {
            return "error";
        }
        model.put("cameras", cameras);
        return "homepage";
    }

    @PostMapping("/submit")
    public String postHomeView(MarsRoverApiDto dto, ModelMap model) throws InvocationTargetException, IllegalAccessException {

        MarsRoverApiResponse roverData = marsRoverApiService.getMarsRoverData(dto);
        model.put("roverData", roverData);
        model.put("defaultDto", dto);
        model.put("cameras", marsRoverApiService.getCamerasOnRover().get(dto.getRoverName()));

        return "homepage";
    }

}
