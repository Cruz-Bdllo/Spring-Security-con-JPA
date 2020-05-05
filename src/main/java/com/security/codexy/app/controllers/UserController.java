package com.security.codexy.app.controllers;

import com.security.codexy.app.entities.Authority;
import com.security.codexy.app.entities.User;
import com.security.codexy.app.services.IAuthorityService;
import com.security.codexy.app.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthorityService authorityService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(path = {"/"})
    public String homeUser(Model model){
        model.addAttribute("title", "Users");
        model.addAttribute("users", userService.findAllUsers());

        return "users/home-user";
    } // end home user

    @GetMapping("/new-user")
    public String viewNewUser(Model model){
        User user = new User();
        model.addAttribute("title", "Add new user");
        model.addAttribute("user", user);
        model.addAttribute("authorities", authorityService.findAllAuthorities());

        return "users/form-user";
    } // end form for add users

    @PostMapping("/save-user")
    public String saveNewUser(@RequestParam int[] idAuthos, User user){
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        for (int i = 0; i < idAuthos.length; i++) {
            Authority authority = authorityService.findAuthorityById(idAuthos[i]);
            user.addAuthorityToList(authority);
        }

        userService.saveUser(user);
        return "redirect:/users/";
    } // end save user

} // end controller
