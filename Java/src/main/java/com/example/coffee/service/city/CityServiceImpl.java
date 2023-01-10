package com.example.coffee.service.city;

import com.example.coffee.protocol.city.CitySelectDto;
import com.example.coffee.protocol.city.CitySelectDtoMapper;
import com.example.coffee.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CitySelectDtoMapper citySelectDtoMapper;

    @Override
    public Set<CitySelectDto> getCityByNameStartWith(String name) {
        return cityRepository.findAllByNameStartsWithIgnoreCase(name).stream().map(citySelectDtoMapper::toDto).collect(Collectors.toSet());
    }
}
