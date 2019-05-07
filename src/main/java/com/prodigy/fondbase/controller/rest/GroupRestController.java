package com.prodigy.fondbase.controller.rest;

import com.prodigy.fondbase.model.security.Group;
import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.service.security.GroupService;
import com.prodigy.fondbase.service.security.ModuleService;
import com.prodigy.fondbase.to.GroupTo;
import com.prodigy.fondbase.to.ModuleTo;
import com.prodigy.fondbase.utils.GroupUtil;
import com.prodigy.fondbase.utils.ModuleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = GroupRestController.GROUP_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupRestController {
    public static final String GROUP_URL = "/rest/group";

    @Autowired
    private GroupService groupService;

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/{id}")
    public Group get(@PathVariable("id") int id) {
        return groupService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> save(@RequestBody GroupTo groupTo) {

        Group group = GroupUtil.createEntityFromTo(groupTo);

        Group created = groupService.save(group);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(GROUP_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping
    public List<Group> getAll() {
        return groupService.getAll();
    }

    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        groupService.enable(id, enabled);
    }

    @GetMapping("/module/{id}")
    public List<ModuleTo> getModules(@PathVariable("id") int id) {
        return groupService.getAllToByGroupId(id);
    }

}
