package com.prodigy.fondbase.controller;

import com.prodigy.fondbase.service.security.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RootController {

    @Autowired
    private MenuCategoryService menuCategoryService;

    @GetMapping(value = {"/", "/favicon.ico"})
    public String root(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.setAttribute("menuCategories", menuCategoryService.getAll());
        return "index";
    }


    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView loginError(@RequestParam(value = "error", required = false) String error) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Неправильный логин или пароль");
        }

        model.setViewName("login");

        return model;

    }

    @GetMapping(value = {"/test"})
    public String test(HttpServletRequest request) {


        return "test";
    }

}
