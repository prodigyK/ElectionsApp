package com.prodigy.fondbase.controller.modules;


import com.prodigy.fondbase.service.logging.LoggingMainService;
import com.prodigy.fondbase.service.security.UserService;
import com.prodigy.fondbase.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "modules/module-logging")
public class LoggingMainController {

    @Autowired
    private LoggingMainService loggingMainService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllByToday(Model model){

        model.addAttribute("logs", loggingMainService.getAllForToday());
        model.addAttribute("users", userService.getAll());
        model.addAttribute("dateFrom", LocalDate.now().format(DateTimeUtil.DATE_FORMATTER));
        model.addAttribute("dateTo", LocalDate.now().format(DateTimeUtil.DATE_FORMATTER));

        return "modules/module-logging";
    }
}
