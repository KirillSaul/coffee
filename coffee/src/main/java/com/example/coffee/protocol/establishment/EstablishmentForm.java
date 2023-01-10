package com.example.coffee.protocol.establishment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class EstablishmentForm {
    private Long selectIdCity;
    private Long selectIdStreet;
    private Integer streetNumber;
    private String firstPhoneNumber;
    private String secondPhoneNumber;
    private List<String> beginDay;
    private List<String> finishDay;
    private List<LocalTime> beginTime;
    private List<LocalTime> finishTime;
}
