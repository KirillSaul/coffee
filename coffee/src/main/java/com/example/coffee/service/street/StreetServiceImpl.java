package com.example.coffee.service.street;

import com.example.coffee.protocol.street.StreetSelectDto;
import com.example.coffee.protocol.street.StreetSelectDtoMapper;
import com.example.coffee.repository.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StreetServiceImpl implements StreetService {
    private final StreetRepository streetRepository;
    private final StreetSelectDtoMapper streetSelectDtoMapper;

    @Override
    public Set<StreetSelectDto> getStreetByNameStartWith(String name) {
        return streetRepository.findAllByNameStartsWithIgnoreCase(name).stream().map(streetSelectDtoMapper::toDto).collect(Collectors.toSet());
    }
}
