package com.example.coffee.protocol.additive;

import com.example.coffee.entity.AdditiveCategory;
import org.mapstruct.Mapper;

@Mapper
public interface AdditiveCategoryMapper {
    AdditiveCategoryListDto toDto(AdditiveCategory source);
    AdditiveCategory toEntity(AdditiveCategoryListDto destination);
}
