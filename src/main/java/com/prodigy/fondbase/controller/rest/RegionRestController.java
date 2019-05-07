package com.prodigy.fondbase.controller.rest;

import com.prodigy.fondbase.model.Region;
import com.prodigy.fondbase.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RegionRestController.REGION_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegionRestController {
    public static final String REGION_URL = "/rest/region";

    @Autowired
    private RegionService regionService;

    @GetMapping("/{id}")
    public Region get(@PathVariable("id") int id) {
        return regionService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        regionService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Region> save(@RequestBody Region region) {
        Region created = regionService.save(region);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REGION_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping
    public List<Region> getAll() {
        return regionService.getAll();
    }

}
