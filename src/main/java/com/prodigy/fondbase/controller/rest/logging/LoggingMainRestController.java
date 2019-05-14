package com.prodigy.fondbase.controller.rest.logging;

import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.model.logging.LoggingChanges;
import com.prodigy.fondbase.model.logging.LoggingMain;
import com.prodigy.fondbase.service.logging.LoggingMainService;
import com.prodigy.fondbase.to.SearchSubscriberTo;
import com.prodigy.fondbase.to.logging.LoggingFilterTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = LoggingMainRestController.LOGGING_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoggingMainRestController {

    public static final String LOGGING_URL = "/rest/logging";

    @Autowired
    private LoggingMainService loggingMainService;

    @GetMapping
    private ResponseEntity<List<LoggingMain>> getAllForToday() {

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(LOGGING_URL + "/{id}")
                .buildAndExpand("getAll").toUri();

        return ResponseEntity.created(uriOfNewResource).body(loggingMainService.getAllForToday());
    }

    @GetMapping(value = "/details/{id}")
    private ResponseEntity<List<LoggingChanges>> getDetails(@PathVariable("id") int id) {

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(LOGGING_URL + "/details/{id}")
                .buildAndExpand("getDetails").toUri();

        return ResponseEntity.created(uriOfNewResource).body(loggingMainService.get(id).getChanges());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoggingMain>> getResult(@RequestBody LoggingFilterTo filterTo) {

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(LOGGING_URL + "/{id}")
                .buildAndExpand(filterTo.getUserId()).toUri();

        if(filterTo.getDateFrom() == null || filterTo.getDateTo() == null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }

        List<LoggingMain> list = loggingMainService.getByUserBetweenDates(filterTo.getUserId(), filterTo.getDateFrom(), filterTo.getDateTo());

        return ResponseEntity.created(uriOfNewResource).body(list);
    }
}
