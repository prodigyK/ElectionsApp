package com.prodigy.fondbase.controller.modules;

import com.prodigy.fondbase.model.District;
import com.prodigy.fondbase.service.CityService;
import com.prodigy.fondbase.service.DistrictService;
import com.prodigy.fondbase.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "modules/module-search-add-person")
public class SearchAddPersonController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private CityService cityService;

    @Autowired
    private DistrictService districtService;

    @GetMapping
    public String root(Model model){

        model.addAttribute("regions", regionService.getAll());
        model.addAttribute("cities", cityService.getAllByRegionId(1001));
        model.addAttribute("districts", districtService.getAllByCityId(1001));

        return "modules/module-search-add-person";
    }

}
