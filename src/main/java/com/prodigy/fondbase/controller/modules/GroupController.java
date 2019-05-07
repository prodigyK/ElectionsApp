package com.prodigy.fondbase.controller.modules;


import com.prodigy.fondbase.model.security.Group;
import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.model.validator.GroupValidator;
import com.prodigy.fondbase.service.security.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;

@Controller
@RequestMapping(value = "modules/module-groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupValidator validator;

    @InitBinder("group")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }


    @RequestMapping(value = "")
    public String root(Model model) {

        return "modules/module-groups";
    }

    @GetMapping(value = "{id}")
    public String root(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("group", groupService.get(id));

        return "modules/module-groups-edit";
    }

    @PostMapping(value = "edit")
    public String edit(@ModelAttribute("group") @Validated Group group, BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("group", group);
            return "modules/module-groups-edit";
        }
        return "modules/module-groups";
    }

    @GetMapping(value = "add")
    public String add(Model model){
        model.addAttribute("group", new Group());

        return "modules/module-groups-edit";
    }
}
