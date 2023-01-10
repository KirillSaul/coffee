package com.example.coffee.protocol.city;

import com.example.coffee.entity.City;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface CitySelectDtoMapper {
    CitySelectDto toDto(City city);
}
