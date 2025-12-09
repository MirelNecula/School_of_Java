package org.example.sqch10ex3.controllers;

import org.example.sqch10ex3.model.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @GetMapping("/france")
    public Country france(){
        return Country.of("France", 67);
    }
}
