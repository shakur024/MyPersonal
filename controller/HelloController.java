package com.heroeducation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.ModelMap;

@RestController
public class HelloController {
	@GetMapping("/hello")
    public String home() {
		System.out.println("Hello from test");
        return "Hello From HeroEducation";
        
    }
}