package com.security.codexy.app.controllers;

import com.security.codexy.app.services.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IAuthorityService authorityService;

    @GetMapping("/")
    public String homeRole(Model model){
        model.addAttribute("title", "Lista de roles permitidos");
        model.addAttribute("roles", authorityService.findAllAuthorities());

        return "roles/home-role";
    } // end home

} // end controller
