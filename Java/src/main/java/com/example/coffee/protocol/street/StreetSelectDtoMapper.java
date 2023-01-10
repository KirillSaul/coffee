package com.example.coffee.protocol.street;

import com.example.coffee.entity.Street;
import org.mapstruct.Mapper;

@Mapper
public interface StreetSelectDtoMapper {
    StreetSelectDto toDto(Street street);
}
