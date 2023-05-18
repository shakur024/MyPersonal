package com.heroeducation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.heroeducation.dao.SightDAO;
import com.heroeducation.model.Sight;

@Controller
public class HomeController {
	private  SightDAO sightDAO;
	
    @GetMapping("/")
    public String home(Model model) {
//    	List<Sight> sightings = sightDAO.getTenSightings();
//        model.addAttribute("sightings", sightings);
        // Add any necessary model attributes for the home page
        return "Home";
    }

    // Add other mappings and methods as needed
}
