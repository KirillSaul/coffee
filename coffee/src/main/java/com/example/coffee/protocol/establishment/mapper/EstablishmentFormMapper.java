package com.example.coffee.protocol.establishment.mapper;

import com.example.coffee.entity.Establishment;
import com.example.coffee.entity.schedule.DaysOfTheWeek;
import com.example.coffee.entity.schedule.Schedule;
import com.example.coffee.protocol.establishment.EstablishmentForm;
import org.mapstruct.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public abstract class EstablishmentFormMapper {

    @AfterMapping
    public void saveSchedule(EstablishmentForm establishmentForm, @MappingTarget Establishment establishment) {
        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < establishmentForm.getBeginDay().size(); i++) {
            schedules.add(
                    toSchedule(establishmentForm.getBeginDay().get(i),
                            establishmentForm.getFinishDay().isEmpty() ? null : establishmentForm.getFinishDay().get(i),
                            establishmentForm.getBeginTime().get(i),
                            establishmentForm.getFinishTime().get(i),
                            establishment
                    )
            );
        }
        establishment.setSchedules(schedules);
    }

    public abstract Schedule toSchedule(String beginDay, String finishDay, LocalTime beginTime, LocalTime finishTime, Establishment establishment);

    @Mapping(target = "city.idCity", source = "establishmentForm.selectIdCity")
    @Mapping(target = "street.idStreet", source = "establishmentForm.selectIdStreet")
    public abstract Establishment toEntity(EstablishmentForm establishmentForm, Long idEstablishment);

    @Mapping(target = "city.idCity", source = "establishmentForm.selectIdCity")
    @Mapping(target = "street.idStreet", source = "establishmentForm.selectIdStreet")
    public abstract Establishment toEntity(EstablishmentForm establishmentForm);

    public DaysOfTheWeek toDaysOfTheWeek(String dayOfTheWeekString) {
        return dayOfTheWeekString.isBlank() ? null : DaysOfTheWeek.valueOf(dayOfTheWeekString);
    }

}
