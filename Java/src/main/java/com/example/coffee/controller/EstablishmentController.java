package com.example.coffee.controller;

import com.example.coffee.entity.schedule.DaysOfTheWeek;
import com.example.coffee.protocol.establishment.EstablishmentForm;
import com.example.coffee.protocol.establishment.EstablishmentTableDto;
import com.example.coffee.service.establishment.EstablishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/establishment")
@RequiredArgsConstructor
public class EstablishmentController {
    private final EstablishmentService establishmentService;

    @GetMapping
    public String getEstablishmentPage() {
        return "establishment/establishments";
    }

    @GetMapping("/create")
    public String getCreateEstablishmentPage(@ModelAttribute EstablishmentForm establishmentForm) {
        return "establishment/createEstablishment";
    }

    @GetMapping("/get-new-schedule")
    public String getNewSchedule() {
        return "establishment/scheduleFragment::schedule";
    }

    @PostMapping("/create")
    public String createEstablishmentPage(@ModelAttribute EstablishmentForm establishmentForm) {
        establishmentService.createEstablishment(establishmentForm);
        return "redirect:./";
    }

    @GetMapping("/edit/{idEstablishment}")
    public String getEditEstablishmentPage(@PathVariable(name = "idEstablishment") Long idEstablishment, Model model) {
        model.addAttribute("establishment", establishmentService.findByIdForEdit(idEstablishment));
        return "establishment/editEstablishment";
    }

    @PostMapping("/edit/{idEstablishment}")
    public String updateEstablishment(@PathVariable(name = "idEstablishment") Long idEstablishment, EstablishmentForm establishmentForm) {
        establishmentService.updateEstablishment(idEstablishment, establishmentForm);
        return "redirect:../";
    }

    @GetMapping("/active")
    @ResponseBody
    public Page<EstablishmentTableDto> getAllActiveByCityId(@RequestParam(value = "selectCityEstablishmentsActive", required = false) Set<Long> idCities,
                                                            @RequestParam(value = "activePage", defaultValue = "0") Integer page,
                                                            @RequestParam(value = "activePageSize", defaultValue = "10") Integer pageSize) {
            return isNull(idCities) || idCities.isEmpty()
                    ? establishmentService.getAllActive(page, pageSize)
                    : establishmentService.getAllActiveByCityId(idCities, page, pageSize);
    }

    @GetMapping("/deleted")
    @ResponseBody
    public Page<EstablishmentTableDto> getAllDeletedByCityId(@RequestParam(value = "selectCityEstablishmentsDeleted", required = false) Set<Long> idCities,
                                                             @RequestParam(value = "deletedPage", defaultValue = "0") Integer page,
                                                             @RequestParam(value = "deletedPageSize", defaultValue = "10") Integer pageSize) {
            return isNull(idCities) || idCities.isEmpty()
                    ? establishmentService.getAllDeleted(page, pageSize)
                    : establishmentService.getAllDeletedByCityId(idCities, page, pageSize);
    }

    @GetMapping("/delete/{idEstablishment}")
    @ResponseBody
    public HttpStatus deleteEstablishment(@PathVariable(name = "idEstablishment") Long idEstablishment) {
        establishmentService.deleteEstablishmentById(idEstablishment);
        return HttpStatus.OK;
    }

    @GetMapping("/recover/{idEstablishment}")
    @ResponseBody
    public HttpStatus recoverEstablishment(@PathVariable(name = "idEstablishment") Long idEstablishment) {
        establishmentService.recoverEstablishmentById(idEstablishment);
        return HttpStatus.OK;
    }

    @ModelAttribute
    public void daysOfTheWeekToRequest(Model model) {
        model.addAttribute("daysOfTheWeekEnum", DaysOfTheWeek.values());
        model.addAttribute("daysOfTheWeekEnumValues", Arrays.stream(DaysOfTheWeek.values()).map(DaysOfTheWeek::getValue).collect(Collectors.toList()));
    }
}
