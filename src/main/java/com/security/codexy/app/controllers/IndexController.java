package com.security.codexy.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path = {"/"})
    public String index(Model model){
        model.addAttribute("title", "Home Page");

        return "index";
    } // end index

} // end controller