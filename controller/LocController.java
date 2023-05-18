package com.heroeducation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.heroeducation.dao.LocDAO;
import com.heroeducation.model.Loc;

@Controller
public class LocController {
	private final LocDAO locDAO;

	public LocController(LocDAO locDAO) {
		this.locDAO = locDAO;
	}

	@GetMapping("/locations")
	public String getAllLocations(Model model) {
		List<Loc> locations = locDAO.getAllLocations();
		model.addAttribute("locations", locations);
		System.out.println(locations.toString());
		return "Location";
	}

	@GetMapping("/CreateLocation")
	public String showLocationForm(Model model) {
		Loc location = new Loc();
		model.addAttribute("location", location);
		System.out.println("heloo from ");
		return "location-form";
		
		
	}

	@PostMapping("/CreateLocation")
	public String saveLocation(@ModelAttribute("location") Loc location) {
		locDAO.createLocation(location);
		return "redirect:/locations";
	}

	@GetMapping("/locations/{id}/edit")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		Loc location = locDAO.getLocationById(id);
		model.addAttribute("location", location);
		return "LocationUpdate";
	}

	@PostMapping("/locations/{id}/edit")
	public String updateLocation(@PathVariable("id") Long id, @ModelAttribute("location") Loc location) {
		locDAO.updateLocation(location);
		return "redirect:/locations";
	}

	@GetMapping("/locations/{id}/delete")
	public String deleteLocation(@PathVariable("id") Long id) {
		locDAO.deleteLocation(id);
		return "redirect:/locations";
	}

}
