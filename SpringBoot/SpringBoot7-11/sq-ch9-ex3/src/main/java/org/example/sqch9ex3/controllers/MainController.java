package org.example.sqch9ex3.controllers;

import org.example.sqch9ex3.services.LoggedUserManagementService;
import org.example.sqch9ex3.services.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;

    public MainController(LoggedUserManagementService loggedUserManagementService, LoginCountService loginCountService) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    @GetMapping("/main")
    public String home(
            @RequestParam(required = false) String logout,
            Model model
    ) {
        if (logout != null) {
            loggedUserManagementService.setUserName(null);
        }

        String username = loggedUserManagementService.getUserName();
        int count = loginCountService.getCount();

        if (username == null) {
            return "redirect:/";
        }

        model.addAttribute("username" , username);
        model.addAttribute("loginCount", count);

        return "main.html";
    }
}