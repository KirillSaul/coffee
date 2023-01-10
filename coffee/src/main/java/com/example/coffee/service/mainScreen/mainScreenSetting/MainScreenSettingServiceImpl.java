package com.example.coffee.service.mainScreen.mainScreenSetting;

import com.example.coffee.entity.mainScreen.MainScreenSetting;
import com.example.coffee.repository.MainScreenSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MainScreenSettingServiceImpl implements MainScreenSettingService {
    private final MainScreenSettingRepository mainScreenSettingRepository;

    @Override
    @Transactional
    public void save(Integer imageSpeed) {
        Optional<MainScreenSetting> mainScreenSettingOptional =  mainScreenSettingRepository.findByIdMainScreenSettingIsNotNull();
        if (mainScreenSettingOptional.isPresent()) {
            mainScreenSettingOptional.get().setImageSpeed(imageSpeed);
        } else {
            mainScreenSettingRepository.save(new MainScreenSetting(imageSpeed));
        }
    }

    @Override
    public Integer getImageSpeed() {
        Optional<MainScreenSetting> mainScreenSettingOptional = mainScreenSettingRepository.findByIdMainScreenSettingIsNotNull();
        return mainScreenSettingOptional.orElse(new MainScreenSetting()).getImageSpeed();

    }
}
