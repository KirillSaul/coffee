package com.example.coffee.protocol.street;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreetSelectDto {
    private Long idStreet;
    private String name;
}
