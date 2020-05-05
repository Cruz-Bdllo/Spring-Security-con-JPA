package com.security.codexy.app.controllers;

import com.security.codexy.app.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping(path = {"/"})
    public String homeUser(Model model){
        model.addAttribute("title", "Users");
        model.addAttribute("users", userService.findAllUsers());

        return "users/home-user";
    } // end home user

} // end controller
