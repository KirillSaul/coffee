package com.example.coffee.protocol.establishment.mapper;

import com.example.coffee.entity.Establishment;
import com.example.coffee.protocol.establishment.EstablishmentTableDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Mapper
public interface EstablishmentTableDtoMapper {
    @Mapping(target = "cityName", source = "establishment.city.name")
    @Mapping(target = "streetName", source = "establishment.street.name")
    EstablishmentTableDto toDto(Establishment establishment);
}
