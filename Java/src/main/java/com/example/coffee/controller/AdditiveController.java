package com.example.coffee.controller;

import com.example.coffee.service.additiveCategory.AdditiveCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/additive")
@RequiredArgsConstructor
public class AdditiveController {
    private final AdditiveCategoryService additiveCategoryService;

    @GetMapping
    public String getAdditiveCategoriesPage(Model model)
    {
        model.addAttribute("additiveCategories",additiveCategoryService.getAllActive());
        return "additive/additives";
    }

    @GetMapping("/{idAdditiveCategory}")
    public String getAdditivePage(@PathVariable("idAdditiveCategory") Long idAdditiveCategory)
    {
        return "additive/additive";
    }
}
