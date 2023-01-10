package com.example.coffee.controller;

import com.example.coffee.protocol.city.CitySelectDto;
import com.example.coffee.service.city.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    @GetMapping("/name-start-with")
    @ResponseBody
    public Set<CitySelectDto> getActiveCitiesByNameStartWith(String cityName) {
        return cityService.getCityByNameStartWith(cityName);
    }
}
