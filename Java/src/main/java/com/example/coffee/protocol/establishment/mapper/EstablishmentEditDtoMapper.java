package com.example.coffee.protocol.establishment.mapper;

import com.example.coffee.entity.Establishment;
import com.example.coffee.protocol.establishment.EstablishmentEditDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
public interface EstablishmentEditDtoMapper {
    EstablishmentEditDto toDto(Establishment establishment);
}
