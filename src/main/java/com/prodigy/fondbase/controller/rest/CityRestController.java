package com.prodigy.fondbase.controller.rest;

import com.prodigy.fondbase.model.City;
import com.prodigy.fondbase.service.CityService;
import com.prodigy.fondbase.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = CityRestController.CITY_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CityRestController {
    public static final String CITY_URL = "/rest/city";

    @Autowired
    private CityService cityService;

    @GetMapping("/{id}")
    public City get(@PathVariable("id") int id) {
        return cityService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        cityService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> save(@RequestBody City city) {
        City created = cityService.save(city);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CITY_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping
    public List<City> getAll() {
        return cityService.getAll();
    }
}
