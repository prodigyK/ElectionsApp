package com.prodigy.fondbase.controller.modules;

import com.prodigy.fondbase.service.commission.ElectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "modules/module-election")
public class ElectionController {

    private ElectionService electionService;

    @RequestMapping(value = "")
    public String root(Model model) {

        return "modules/module-election";
    }

}
