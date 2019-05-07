package com.prodigy.fondbase.controller.rest;

import com.prodigy.fondbase.model.Region;
import com.prodigy.fondbase.model.security.User;
import com.prodigy.fondbase.service.security.UserService;
import com.prodigy.fondbase.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = UserRestController.USER_URL)
public class UserRestController {
    public static final String USER_URL = "/rest/user";

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return userService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationUtil.getErrorResponse(result);
        }
        if(user.getGroup().isNew()){
            return new ResponseEntity<>("Не выбрана группа", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User created = userService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        userService.enable(id, enabled);
    }

}
