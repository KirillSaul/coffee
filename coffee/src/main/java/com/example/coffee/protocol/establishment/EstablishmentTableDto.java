package com.example.coffee.protocol.establishment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstablishmentTableDto {
    private Long idEstablishment;
    private String cityName;
    private String streetName;
    private Integer streetNumber;

}
