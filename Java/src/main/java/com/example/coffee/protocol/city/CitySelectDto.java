package com.example.coffee.protocol.city;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CitySelectDto {
    private Long idCity;
    private String name;
}
