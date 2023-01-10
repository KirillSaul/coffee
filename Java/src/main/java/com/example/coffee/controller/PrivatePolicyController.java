package com.example.coffee.controller;

import com.example.coffee.service.privatePolicy.PrivatePolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/private-policy")
@RequiredArgsConstructor
public class PrivatePolicyController {
    private final PrivatePolicyService privatePolicyService;

    @GetMapping
    public String getPrivatePolicy(Model model) {
        model.addAttribute("descriptionPrivatePolicy", privatePolicyService.getPrivatePolicy().getDescription());
        return "privatePolicy";
    }

    @PostMapping
    public String savePrivatePolicy(String descriptionPrivatePolicy, HttpServletRequest request) {
        privatePolicyService.save(descriptionPrivatePolicy);
        return "redirect:" + request.getRequestURI();
    }
}
