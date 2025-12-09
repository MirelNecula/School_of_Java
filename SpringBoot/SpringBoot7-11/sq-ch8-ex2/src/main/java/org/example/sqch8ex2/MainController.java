package org.example.sqch8ex2;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

 @RequestMapping("/home")
    public String home(
         @RequestParam String color , Model page) {
     page.addAttribute("color", color);
     page.addAttribute("username" ,"name");
     return"home" ;
 }

}
