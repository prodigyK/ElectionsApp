package com.prodigy.fondbase.controller.modules;

import com.prodigy.fondbase.model.security.Module;
import com.prodigy.fondbase.service.security.MenuCategoryService;
import com.prodigy.fondbase.service.security.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "modules/module-menu")
public class MenuController {

    @Autowired
    private MenuCategoryService menuCategoryService;

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "")
    public String root(Model model) {

        model.addAttribute("menuParents", menuCategoryService.getAllParentsTo());
        model.addAttribute("modules", moduleService.getAllTo());

        return "modules/module-menu";
    }

}
