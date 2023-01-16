package com.marsrover.marsroverapi.service;

import com.marsrover.marsroverapi.dto.MarsRoverApiDto;
import com.marsrover.marsroverapi.response.MarsPhoto;
import com.marsrover.marsroverapi.response.MarsRoverApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class MarsRoverApiService {

    private static final String API_KEY = "SX075VOQVamYL6As11rgSnR12keoS2aOjhRy5GJN";

    private Map<String, List<String>> camerasOnRover = new HashMap<>();

    public MarsRoverApiService() {
        camerasOnRover.put("Opportunity", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
        camerasOnRover.put("Curiosity", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "NAVCAM", "MARDI"));
        camerasOnRover.put("Spirit", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
    }

    /**
     * get photos
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public MarsRoverApiResponse getMarsRoverData(MarsRoverApiDto dto) throws InvocationTargetException, IllegalAccessException {

        RestTemplate restTemplate = new RestTemplate();
        List<String> requestUrls = getApiUrls(dto);
        List<MarsPhoto> photos = new ArrayList<>();
        MarsRoverApiResponse marsRoverApiResponse = new MarsRoverApiResponse();

        requestUrls.forEach(url -> {
            MarsRoverApiResponse tmpResponse = restTemplate.getForObject(url, MarsRoverApiResponse.class);
            if (Objects.nonNull(tmpResponse)) {
                photos.addAll(tmpResponse.getPhotos());
            }
        });

        marsRoverApiResponse.setPhotos(photos);

        return marsRoverApiResponse;
    }

    /**
     * get request urls
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private List<String> getApiUrls(MarsRoverApiDto dto) throws InvocationTargetException, IllegalAccessException {
        List<String> urls = new ArrayList<>();
        Method[] methods = dto.getClass().getMethods();
        for (Method method : methods) {
            // java reflection to check which getCamera method is invoked
            if (method.getName().contains("getCamera") && Boolean.TRUE.equals(method.invoke(dto))) {
                // e.g. getCameraFhaz will get FHAZ
                String cameraName = method.getName().split("getCamera")[1].toUpperCase();
                if (camerasOnRover.get(dto.getRoverName()).contains(cameraName)) {
                    urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + dto.getRoverName()
                            + "/photos?sol=" + dto.getMarsSol()
                            + "&camera=" + cameraName.toUpperCase()
                            + "&api_key=" + API_KEY);
                }
            }
        }
        return urls;
    }

    public Map<String, List<String>> getCamerasOnRover() {
        return camerasOnRover;
    }
}
