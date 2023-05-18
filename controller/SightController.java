package com.heroeducation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.heroeducation.dao.HeroDAO;
import com.heroeducation.dao.LocDAO;
import com.heroeducation.dao.SightDAO;
import com.heroeducation.model.Hero;
import com.heroeducation.model.Loc;
import com.heroeducation.model.Sight;

@Controller
public class SightController {

    private final SightDAO sightDAO;
    private final HeroDAO heroDAO;
    private final LocDAO locDAO;

    public SightController(SightDAO sightDAO, HeroDAO heroDAO, LocDAO locDAO) {
        this.sightDAO = sightDAO;
        this.heroDAO = heroDAO;
        this.locDAO = locDAO;
    }

    @GetMapping("/sightings")
    public String getAllSightings(Model model) {
        List<Sight> sightings = sightDAO.getAllSightings();
        model.addAttribute("sightings", sightings);
        return "sight";
    }

	
	 @GetMapping("/createSightings") 
	 public String showSightingForm(Model model) {
		 Sight sighting = new Sight();
		 List<Hero> heroes = heroDAO.getAllHeroes();
		 List<Loc> locations = locDAO.getAllLocations();
		 model.addAttribute("sight",sighting);
		 model.addAttribute("heros", heroes);
		 model.addAttribute("locations", locations);
		 return "sighting-form"; 
	 }
	 
    
    @PostMapping("createSightings")
    public String addSight(Sight sight, HttpServletRequest request) {
        String heroId = request.getParameter("heroId");
        String locationIds = request.getParameter("locationId");
        
        sight.setHero(heroDAO.getHeroById(Integer.parseInt(heroId)));
        sight.setLocation(locDAO.getLocationById(Long.getLong(locationIds)));
        
        sightDAO.createSighting(sight);
       
  
        return "redirect:/sightings";
    }

	/*
	 * @PostMapping("/createSightings") public String
	 * saveSighting(@ModelAttribute("sight") Sight sighting) {
	 * sightDAO.createSighting(sighting); return "redirect:/sightings"; }
	 */


    @PostMapping("/sightings/{id}/edit")
    public String updateSighting(@PathVariable("id") Long id, @ModelAttribute("sight") Sight sighting) {
        sightDAO.updateSighting(sighting);
        return "redirect:/sightings";
    }


}