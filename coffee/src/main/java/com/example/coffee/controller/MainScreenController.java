package com.example.coffee.controller;

import com.example.coffee.protocol.mainScreen.MainScreenForm;
import com.example.coffee.service.mainScreen.mainScreenImage.MainScreenImageService;
import com.example.coffee.service.mainScreen.mainScreenSetting.MainScreenSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping("/main-screen")
@RequiredArgsConstructor
public class MainScreenController {
    private final MainScreenSettingService mainScreenSettingService;
    private final MainScreenImageService mainScreenImageService;

    @GetMapping
    public String getMainScreenPage(@ModelAttribute MainScreenForm mainScreenForm) {
        return "mainScreen/mainScreen";
    }

    @PostMapping
    public String saveChanges(@Valid MainScreenForm mainScreenForm,
                              BindingResult bindingResult,
                              HttpServletRequest request) {
        try {
            if (nonNull(mainScreenForm.getInputImages()) && !mainScreenForm.getInputImages().isEmpty()) {
                mainScreenImageService.save(mainScreenForm.getInputImages());
            }
            if (nonNull(mainScreenForm.getIdOldImagesForDelete()) && !mainScreenForm.getIdOldImagesForDelete().isEmpty()) {
                mainScreenImageService.deleteImageById(mainScreenForm.getIdOldImagesForDelete());
            }
        } catch (IOException e) {
            bindingResult.reject("405", e.getMessage());
            return "mainScreen/mainScreen";
        }

        mainScreenSettingService.save(mainScreenForm.getImageSpeed());

        return "redirect:" + request.getRequestURI();
    }

    @PostMapping("/get-input-image")
    public String getInputImage(@RequestParam("idInput") Long idInput, Model model) {
        model.addAttribute("idNumber", idInput);
        return "mainScreen/mainScreenFragments::inputImage";
    }

    @ModelAttribute
    public void sendMainScreenInformation(Model model) {
        model.addAttribute("imageSpeed", mainScreenSettingService.getImageSpeed());
        model.addAttribute("images", mainScreenImageService.getAllImages());
    }
}

