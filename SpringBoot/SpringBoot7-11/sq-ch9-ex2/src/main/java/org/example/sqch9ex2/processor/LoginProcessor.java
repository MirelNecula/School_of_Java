package org.example.sqch9ex2.processor;

import org.example.sqch9ex2.service.LoggedUserManagementService;
import org.example.sqch9ex2.service.LoggedUserManagementService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginProcessor {

    private final LoggedUserManagementService loggedUserManagmentService;

    private String userName;
    private String password;

    public LoginProcessor(LoggedUserManagementService loggedUserManagmentService) {
        this.loggedUserManagmentService = loggedUserManagmentService;
    }

    public boolean login() {
        String username = this.getUserName();
        String password = this.getPassword();

        boolean loginResult = false;
        if ("natalie".equals(username) && "password".equals(password)) {
            loggedUserManagmentService.setUserName(username);
            loginResult = true;
        }
        return loginResult;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}