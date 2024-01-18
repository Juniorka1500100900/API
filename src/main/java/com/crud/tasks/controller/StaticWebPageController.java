package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("variable", "My Thymeleaf variable");
        model.addAttribute("one", 1);
        model.addAttribute("two", 2);
        return "index";
    }
}
