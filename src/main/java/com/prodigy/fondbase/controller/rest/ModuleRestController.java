package com.prodigy.fondbase.controller.rest;

import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.service.security.ModuleService;
import com.prodigy.fondbase.to.ModuleTo;
import com.prodigy.fondbase.utils.ModuleUtil;
import com.prodigy.fondbase.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = ModuleRestController.MODULE_URL)
public class ModuleRestController {

    public static final String MODULE_URL = "/rest/module";

    @Autowired
    private ModuleService moduleService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Module get(@PathVariable("id") int id) {
        return moduleService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@Valid @RequestBody ModuleTo moduleTo, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationUtil.getErrorResponse(result);
        }

        Module module = ModuleUtil.createEntityFromTo(moduleTo);
        if(module.getId() != null){
            Module fromDb = moduleService.get(module.getId());
            module.setEnabled(fromDb.isEnabled());
            module.setVisible(fromDb.isVisible());
        }
        moduleService.save(module);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ModuleTo> getAll() {
        return moduleService.getAllTo();
    }

    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        moduleService.enable(id, enabled);
    }

    @PostMapping("/0/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void visible(@PathVariable("id") int id, @RequestParam("visible") boolean visible) {
        moduleService.visible(id, visible);
    }

    @GetMapping(value = "empty", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ModuleTo> getEmpty() {
        return new ArrayList<>();
    }

}
