package com.example.coffee.entity.mainScreen;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "main_screen_settings", schema = "coffee", catalog = "coffee")
public class MainScreenSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_main_screen_setting", nullable = false)
    private Long idMainScreenSetting;

    @Column(name = "image_speed", nullable = false)
    private Integer imageSpeed;

    public MainScreenSetting() {
        imageSpeed = 1;
    }

    public MainScreenSetting(Integer imageSpeed) {
        this.imageSpeed = imageSpeed;
    }
}
