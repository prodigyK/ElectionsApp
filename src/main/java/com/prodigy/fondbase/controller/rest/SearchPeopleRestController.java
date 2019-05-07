package com.prodigy.fondbase.controller.rest;

import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.service.StreetService;
import com.prodigy.fondbase.service.SubscriberService;
import com.prodigy.fondbase.to.MenuTo;
import com.prodigy.fondbase.to.SearchSubscriberTo;
import com.prodigy.fondbase.utils.SearchSubscriberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = SearchPeopleRestController.SEARCH_PEOPLE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchPeopleRestController {

    public static final String SEARCH_PEOPLE_URL = "/rest/search";

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private StreetService streetService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SearchSubscriberTo>> get(@RequestBody SearchSubscriberTo searchSubscriberTo) {

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(SEARCH_PEOPLE_URL + "/{id}")
                .buildAndExpand(searchSubscriberTo.getIin()).toUri();

        List<Subscriber> subscribers = subscriberService.searchPeople(searchSubscriberTo);
        List<SearchSubscriberTo> list = subscriberService.convertSearchEntityList(subscribers);

        return ResponseEntity.created(uriOfNewResource).body(list);

    }

    @GetMapping(value = "lastname/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getLastname(@PathVariable("query") String query) {

        List<String> list = subscriberService.searchByLastname(query);
        return list;
    }

    @GetMapping(value = "firstname/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getFirstname(@PathVariable("query") String query) {

        List<String> list = subscriberService.searchByFirstname(query);
        return list;
    }

    @GetMapping(value = "middlename/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMiddlename(@PathVariable("query") String query) {

        List<String> list = subscriberService.searchByMiddlename(query);
        return list;
    }

    @GetMapping(value = "street/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getStreetName(@PathVariable("query") String query) {

        List<String> list = streetService.searchByStreetName(query);
        return list;
    }

}
