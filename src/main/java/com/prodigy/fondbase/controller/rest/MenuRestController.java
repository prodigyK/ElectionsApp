package com.prodigy.fondbase.controller.rest;

import com.prodigy.fondbase.model.security.MenuCategory;
import com.prodigy.fondbase.service.security.MenuCategoryService;
import com.prodigy.fondbase.to.MenuTo;
import com.prodigy.fondbase.utils.MenuUtil;
import com.prodigy.fondbase.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = MenuRestController.MENU_URL)
public class MenuRestController {
    public static final String MENU_URL = "/rest/menu";

    @Autowired
    private MenuCategoryService menuCategoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuTo> getAll() {
        return menuCategoryService.getAllTo();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuTo get(@PathVariable("id") int id) {
        return menuCategoryService.getTo(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        menuCategoryService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@Valid @RequestBody MenuTo menuTo, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationUtil.getErrorResponse(result);
        }

        MenuCategory menuCategory = MenuUtil.createEntityFromTo(menuTo);

        if (menuCategory.getParent() != null && menuCategory.getModule() == null){
            result.addError(new FieldError("Меню категории", "module", "Необходимо выбрать модуль"));
            return ValidationUtil.getErrorResponse(result);
        }
        if (menuCategory.getParent() == null && menuCategory.getModule() != null){
            result.addError(new FieldError("Меню категории", "module", "Нельзя применить модуль к главному меню"));
            return ValidationUtil.getErrorResponse(result);
        }
        if (menuCategory.getParent() != null && menuCategory.getModule() != null) {
            MenuCategory checkItem = menuCategoryService.getByModuleId(menuCategory.getModule().getId());
            if (checkItem != null && !checkItem.equals(menuCategory)) {
                result.addError(new FieldError("Меню категории", "module", "Такой модуль уже привязан к другому пункту меню"));
                return ValidationUtil.getErrorResponse(result);
            }
        }

        menuCategoryService.save(menuCategory);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
