package com.example.coffee.entity;

import com.example.coffee.entity.schedule.Schedule;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

import static java.util.Objects.isNull;

@NamedEntityGraphs(
        value = {
                @NamedEntityGraph(
                        name = Establishment.GRAPH_CITY_STREET,
                        attributeNodes = {
                                @NamedAttributeNode("city"),
                                @NamedAttributeNode("street")
                        }
                ),
                @NamedEntityGraph(
                        name = Establishment.GRAPH_ALL,
                        attributeNodes = {
                                @NamedAttributeNode("city"),
                                @NamedAttributeNode("street"),
                                @NamedAttributeNode("schedules")
                        }
                )
        }
)
@Table(name = "establishments", schema = "coffee", catalog = "coffee")
@Data
@AllArgsConstructor
@Builder
@Entity
public class Establishment {
    public static final String GRAPH_CITY_STREET = "cityStreet";
    public static final String GRAPH_ALL = "all";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_establishment", nullable = false)
    private Long idEstablishment;

    @Column(name = "street_number", nullable = false)
    private Integer streetNumber;

    @Column(name = "first_phone_number", nullable = true)
    private String firstPhoneNumber;

    @Column(name = "second_phone_number", nullable = true)
    private String secondPhoneNumber;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_city", nullable = false)
    @ToString.Exclude
    private City city;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_street", nullable = false)
    @ToString.Exclude
    private Street street;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "establishment")
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private Collection<Schedule> schedules;

    public Establishment() {
        active = true;
    }

    public void setSchedules(Collection<Schedule> schedules) {
        if (isNull(this.schedules)) {
            this.schedules = schedules;
        } else {
            this.schedules.clear();
            this.schedules.addAll(schedules);
        }
    }
}
