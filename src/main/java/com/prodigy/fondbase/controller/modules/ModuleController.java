package com.prodigy.fondbase.controller.modules;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "modules/module-modules")
public class ModuleController {

    @RequestMapping(value = "")
    public String root(){
        return "modules/module-modules";
    }
}
