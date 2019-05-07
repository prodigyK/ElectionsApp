package com.prodigy.fondbase.controller.modules;

import com.prodigy.fondbase.service.security.GroupService;
import com.prodigy.fondbase.service.security.ModuleService;
import com.prodigy.fondbase.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "modules/module-operators")
public class OperatorController {

    private final String MODULE_OPERATORS = "MODULE_OPERATORS";

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

//    @PreAuthorize("hasAuthority('MODULE_OPERATORS')")
    @Secured("MODULE_OPERATORS")
    @RequestMapping(value = "")
    public String root(Model model){

        model.addAttribute("groups", groupService.getAll());

        return "modules/module-operators";
    }
}
