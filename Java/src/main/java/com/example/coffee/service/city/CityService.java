package com.example.coffee.service.city;

import com.example.coffee.protocol.city.CitySelectDto;

import java.util.Set;

public interface CityService {
    Set<CitySelectDto> getCityByNameStartWith(String name);
}
