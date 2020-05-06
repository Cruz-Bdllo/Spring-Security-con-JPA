package com.security.codexy.app.controllers;

import com.security.codexy.app.entities.Authority;
import com.security.codexy.app.services.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/save-role")
    public String saveRole(@RequestParam String roleName, @RequestParam String roleDes){
        Authority authority = new Authority();
        authority.setName(roleName.toUpperCase());
        authority.setDescription(roleDes);
        authorityService.saveAuthority(authority);

        return "redirect:/roles/";
    } // end save

} // end controller
