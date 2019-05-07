package com.prodigy.fondbase.controller.rest;


import com.prodigy.fondbase.model.Address;
import com.prodigy.fondbase.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = AddressRestController.ADDRESS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressRestController {

    public static final String ADDRESS_URL = "/rest/address";

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public Address get(@PathVariable("id") int id) {
        return addressService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        addressService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> save(@RequestBody Address address) {
        Address created = addressService.save(address);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADDRESS_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
