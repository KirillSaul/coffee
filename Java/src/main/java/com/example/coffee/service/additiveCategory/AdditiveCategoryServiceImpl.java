package com.example.coffee.service.additiveCategory;

import com.example.coffee.protocol.additive.AdditiveCategoryMapper;
import com.example.coffee.protocol.additive.AdditiveCategoryListDto;
import com.example.coffee.repository.AdditiveCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdditiveCategoryServiceImpl implements AdditiveCategoryService{
    private final AdditiveCategoryRepository additiveCategoryRepository;
    private final AdditiveCategoryMapper additiveCategoryMapper;

    @Override
    public List<AdditiveCategoryListDto> getAllActive() {
        return additiveCategoryRepository.findAll()
                .stream()
                .map(additiveCategoryMapper::toDto).collect(Collectors.toList());
    }
}
