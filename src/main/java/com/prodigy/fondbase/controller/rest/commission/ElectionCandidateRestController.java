package com.prodigy.fondbase.controller.rest.commission;

import com.prodigy.fondbase.model.commission.ElectionCandidate;
import com.prodigy.fondbase.model.upload.UploadCandidate;
import com.prodigy.fondbase.service.commission.ElectionCandidateService;
import com.prodigy.fondbase.to.ElectionCandidateTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ElectionCandidateRestController.ELECTION_URL)
public class ElectionCandidateRestController {
    public static final String ELECTION_URL = "/rest/election/candidate";

    @Autowired
    private ElectionCandidateService electionCandidateService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ElectionCandidate> getAll() {
        return electionCandidateService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ElectionCandidate get(@PathVariable("id") int id) {
        return electionCandidateService.get(id);
    }

    @GetMapping(value = "/tech/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ElectionCandidate> getTechCandidates(@PathVariable("id") int id) {
        return electionCandidateService.getTechCandidates(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ElectionCandidate> save(@Valid @RequestBody ElectionCandidate candidate, BindingResult result) {
/*
        if (result.hasErrors()) {
            return ValidationUtil.getErrorResponse(result);
        }
*/

        if(!candidate.isNew()){
            ElectionCandidate cand = electionCandidateService.get(candidate.getId());
            cand.setName(candidate.getName());
            cand.setTop(candidate.isTop());
            cand.setColor(candidate.getColor());
            candidate = cand;
        }
        ElectionCandidate created = electionCandidateService.save(candidate);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ELECTION_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = "/tech", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveTechs(@Valid @RequestBody ElectionCandidateTo candidateTo, BindingResult result){

        ElectionCandidate candidate = electionCandidateService.get(candidateTo.getId());
        List<ElectionCandidate> techs = candidateTo.getTechCandidates();

        electionCandidateService.saveTechnicals(candidate, techs);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/enabled/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        electionCandidateService.enable(id, enabled);
    }

    @PostMapping("/leftOut/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void leftOut(@PathVariable("id") int id, @RequestParam("leftOut") boolean enabled) {
        electionCandidateService.leftOut(id, enabled);
    }

    @PostMapping("/ourCand/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void ourCand(@PathVariable("id") int id, @RequestParam("ourCand") boolean enabled) {
        electionCandidateService.ourCand(id, enabled);
    }

    @PostMapping("/ourTechCand/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void ourTechCand(@PathVariable("id") int id, @RequestParam("ourTechCand") boolean enabled) {
        electionCandidateService.ourTechCand(id, enabled);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(@ModelAttribute("formData") UploadCandidate uploadfile){

        File convFile = new File(uploadfile.getFile().getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)){
            convFile.createNewFile();
            fos.write(uploadfile.getFile().getBytes());

        } catch (IOException e) {
            return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
        }

        boolean isUploaded = electionCandidateService.uploadCandidates(convFile, uploadfile.getElection());

        if(!isUploaded){
            return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }


}
