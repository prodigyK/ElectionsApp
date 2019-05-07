package com.prodigy.fondbase.controller.rest;

import com.prodigy.fondbase.model.House;
import com.prodigy.fondbase.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = HouseRestController.HOUSE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HouseRestController {

    public static final String HOUSE_URL = "/rest/house";

    @Autowired
    private HouseService houseService;

    @GetMapping("/{id}")
    public House get(@PathVariable("id") int id) {
        return houseService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        houseService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<House> save(@RequestBody House house) {
        House created = houseService.save(house);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(HOUSE_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
