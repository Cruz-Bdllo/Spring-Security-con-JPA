package com.security.codexy.app.controllers;

import com.security.codexy.app.entities.User;
import com.security.codexy.app.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    @GetMapping(path = {"/"})
    public String index(Model model){
        model.addAttribute("title", "Home Page");

        return "index";
    } // end index

    @GetMapping("/error403")
    public String error403(Model model){
        model.addAttribute("title", "Acceso denegado");

        return "error403";
    } // end of error

    @RequestMapping("/login")
    public String login(Model model, Principal dataUser){
        model.addAttribute("title", "Inicia Sesión");

        //User user = (dataUser != null) ? userService.findUserByEmail(dataUser.getName()) : null;

        // prevenir el acceso al login cuando ya se inicio sesión
        return (dataUser != null) ? "redirect:/" : "/login";
    } // end login


} // end controller
