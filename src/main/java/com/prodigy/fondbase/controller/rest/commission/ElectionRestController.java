package com.prodigy.fondbase.controller.rest.commission;

import com.prodigy.fondbase.model.commission.Election;
import com.prodigy.fondbase.service.commission.ElectionService;
import com.prodigy.fondbase.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ElectionRestController.ELECTION_URL)
public class ElectionRestController {
    public static final String ELECTION_URL = "/rest/election";

    @Autowired
    private ElectionService electionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Election> getAll() {
        return electionService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Election get(@PathVariable("id") int id) {
        return electionService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@Valid @RequestBody Election election, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationUtil.getErrorResponse(result);
        }

        LocalDate date = election.getElectionDate();
        date = date.plusDays(1);
        election.setElectionDate(date);
        Election created = electionService.save(election);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        electionService.enable(id, enabled);
    }

}
