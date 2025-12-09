package org.example.sqch9ex2.controllers;

import org.example.sqch9ex2.processor.LoginProcessor;
import org.example.sqch9ex2.service.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagmentService;
    private final LoginProcessor loginProcessor;

    public MainController(LoggedUserManagementService loggedUserManagmentService, LoginProcessor loginProcessor) {
        this.loggedUserManagmentService = loggedUserManagmentService;
        this.loginProcessor = loginProcessor;
    }


    @GetMapping("/main")
    public String home(
            @RequestParam(required = false) String logout, Model model ) {
        if (logout != null) {
            loggedUserManagmentService.setUserName(null);
        }
        String username = loggedUserManagmentService.getUserName();
        if (username == null) {
            return "redirect:/";
        }
        model.addAttribute("userName", username);
        return "main";
    }

}
