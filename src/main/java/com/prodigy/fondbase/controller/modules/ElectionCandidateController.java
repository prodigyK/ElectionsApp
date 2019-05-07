package com.prodigy.fondbase.controller.modules;

import com.prodigy.fondbase.model.commission.ElectionCandidate;
import com.prodigy.fondbase.model.upload.UploadCandidate;
import com.prodigy.fondbase.service.commission.ElectionCandidateService;
import com.prodigy.fondbase.service.commission.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "modules/module-election-candidates")
public class ElectionCandidateController {

    @Autowired
    private ElectionCandidateService electionCandidateService;

    @Autowired
    private ElectionService electionService;

    @RequestMapping(value = "")
    public String root(Model model) {

        return "modules/module-election-candidates";
    }

    @GetMapping(value = "{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("candidate", electionCandidateService.get(id));
        model.addAttribute("edit", true);

        return "modules/module-election-candidates-edit";
    }

    @GetMapping(value = "add")
    public String add(Model model) {

        model.addAttribute("candidate", new ElectionCandidate());
        model.addAttribute("edit", false);
        model.addAttribute("elections", electionService.getAll());

        return "modules/module-election-candidates-edit";
    }

    @GetMapping(value = "upload")
    public String upload(Model model) {

        model.addAttribute("elections", electionService.getAll());

        return "modules/module-election-candidates-upload";
    }

}
