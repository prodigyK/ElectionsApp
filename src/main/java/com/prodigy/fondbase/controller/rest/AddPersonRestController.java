package com.prodigy.fondbase.controller.rest;

import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.service.SubscriberService;
import com.prodigy.fondbase.to.SubscriberTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = AddPersonRestController.PERSON_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AddPersonRestController {

    public static final String PERSON_URL = "/rest/person";

    @Autowired
    private SubscriberService subscriberService;

    @GetMapping("/{id}")
    public SubscriberTo get(@PathVariable("id") int id) {

        return subscriberService.getTo(id);
    }

    @RequestMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<SubscriberTo> save(@RequestBody SubscriberTo subscriberTo){

        Subscriber subscriber = subscriberService.convertToAndSave(subscriberTo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
