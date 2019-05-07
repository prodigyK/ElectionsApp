package com.prodigy.fondbase.controller.rest;

import com.prodigy.fondbase.model.District;
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
@RequestMapping(value = DistrictRestController.DISTRICT_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DistrictRestController {

    public static final String DISTRICT_URL = "/rest/district";

    @Autowired
    private DistrictService districtService;

    @GetMapping("/{id}")
    public District get(@PathVariable("id") int id) {
        return districtService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        districtService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<District> save(@RequestBody District district) {
        District created = districtService.save(district);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DISTRICT_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping
    public List<District> getAll() {
        return districtService.getAll();
    }

}
