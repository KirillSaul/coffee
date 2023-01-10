package com.example.coffee.service.additiveCategory;

import com.example.coffee.protocol.additive.AdditiveCategoryListDto;

import java.util.List;

public interface AdditiveCategoryService {
    List<AdditiveCategoryListDto> getAllActive();
}
