package com.example.coffee.service.street;

import com.example.coffee.protocol.street.StreetSelectDto;

import java.util.Set;

public interface StreetService {
    Set<StreetSelectDto> getStreetByNameStartWith(String name);
}
