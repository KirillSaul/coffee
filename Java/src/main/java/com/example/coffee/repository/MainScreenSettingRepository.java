package com.example.coffee.repository;

import com.example.coffee.entity.mainScreen.MainScreenSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainScreenSettingRepository extends JpaRepository<MainScreenSetting,Long> {
    Optional<MainScreenSetting> findByIdMainScreenSettingIsNotNull();
}
