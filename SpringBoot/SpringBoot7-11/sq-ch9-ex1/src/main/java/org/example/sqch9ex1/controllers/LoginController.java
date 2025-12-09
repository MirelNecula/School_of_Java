package org.example.sqch9ex1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import processor.LoginProcessor;

@Controller
public class LoginController {

    private LoginProcessor loginProcessor;
    public LoginController(LoginProcessor loginProcessor) {
        this.loginProcessor = loginProcessor;
    }

    @GetMapping("/")
    public String loginGet(){
        return "login";
    }

    @PostMapping("/")
    public String loginPost(
        @RequestParam String username,
        @RequestParam String password, Model model) {
    boolean loggedIn = loginProcessor.login(username, password);

    if(loggedIn ) {
        model.addAttribute("message", "Login successful!");
    }else {
        model.addAttribute("message", "Login failed. Please try again.");
    }
    return "login";
        }
    }

