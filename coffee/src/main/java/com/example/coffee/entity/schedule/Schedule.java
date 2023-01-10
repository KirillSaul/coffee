package com.example.coffee.entity.schedule;

import com.example.coffee.entity.Establishment;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "schedule", schema = "coffee", catalog = "coffee")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule", nullable = false)
    private Long idSchedule;

    @Column(name = "begin_day", nullable = false)
    @Enumerated(EnumType.STRING)
    private DaysOfTheWeek beginDay;

    @Column(name = "finish_day", nullable = true)
    @Enumerated(EnumType.STRING)
    private DaysOfTheWeek finishDay;

    @Column(name = "begin_time", nullable = false)
    private LocalTime beginTime;

    @Column(name = "finish_time", nullable = false)
    private LocalTime finishTime;

    @ManyToOne
    @JoinColumn(name = "id_establishment",nullable = false)
    private Establishment establishment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Schedule schedule = (Schedule) o;

        return Objects.equals(idSchedule, schedule.idSchedule);
    }

    @Override
    public int hashCode() {
        return 1998924127;
    }
}
