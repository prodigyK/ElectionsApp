package com.prodigy.fondbase.controller.modules;

import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "modules/module-add-person")
public class AddPersonController {

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private CityService cityService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private StreetService streetService;

    @GetMapping
    public String root(Model model){

        model.addAttribute("regions", regionService.getAll());
        model.addAttribute("cities", cityService.getAllByRegionId(1001));
        model.addAttribute("districts", districtService.getAllByCityId(1001));
        model.addAttribute("streets", streetService.getAllByCity(1001));
        model.addAttribute("person", new Subscriber());

        return "modules/module-add-person";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){


        model.addAttribute("regions", regionService.getAll());
        model.addAttribute("cities", cityService.getAllByRegionId(1001));
        model.addAttribute("districts", districtService.getAllByCityId(1001));
        model.addAttribute("streets", streetService.getAllByCity(1001));
        model.addAttribute("person", subscriberService.getTo(id));

        return "modules/module-add-person";
    }



}
