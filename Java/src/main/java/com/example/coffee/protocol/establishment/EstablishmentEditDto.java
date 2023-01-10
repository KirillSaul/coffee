package com.example.coffee.protocol.establishment;

import com.example.coffee.entity.City;
import com.example.coffee.entity.Street;
import com.example.coffee.entity.schedule.Schedule;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class EstablishmentEditDto{
    private Integer streetNumber;
    private String firstPhoneNumber;
    private String secondPhoneNumber;
    private City city;
    private Street street;
    private Collection<Schedule> schedules;
}
