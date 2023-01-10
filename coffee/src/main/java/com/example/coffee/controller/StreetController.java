package com.example.coffee.controller;

import com.example.coffee.protocol.street.StreetSelectDto;
import com.example.coffee.service.street.StreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/street")
public class StreetController {
    private final StreetService streetService;

    @GetMapping("/name-start-with")
    @ResponseBody
    public Set<StreetSelectDto> getActiveCitiesByNameStartWith(String streetName) {
        return streetService.getStreetByNameStartWith(streetName);
    }
}
