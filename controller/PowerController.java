package com.heroeducation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.heroeducation.dao.PowerDAO;
import com.heroeducation.model.Loc;
import com.heroeducation.model.Org;
import com.heroeducation.model.Power;

@Controller
public class PowerController {

    private final PowerDAO powerDAO;
    
    @PostMapping("/CreatePower")
	public String saveLocation(@ModelAttribute("power") Power power) {
    	powerDAO.createPower(power);
		return "redirect:/powers";
	}
//
    public PowerController(PowerDAO powerDAO) {
        this.powerDAO = powerDAO;
    }
//
    @GetMapping("/powers")
    public String getAllPowers(Model model) {
        List<Power> powers = powerDAO.getAllPowers();
        model.addAttribute("powers", powers);
        return "power";
    }
    
    @GetMapping("/powers/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
    	Power power = powerDAO.getPowerById(id);
        model.addAttribute("power", power);
        return "powerUpdate";
    }

    @PostMapping("/powers/{id}/edit")
    public String updateOrganization(@PathVariable("id") Long id, @ModelAttribute("power") Power power) {
    	powerDAO.updatePower(power);
        return "redirect:/powers";
    }

    @GetMapping("/powers/{id}/delete")
    public String deleteOrganization(@PathVariable("id") Long id) {
    	powerDAO.deletePower(id);
        return "redirect:/powers";
    }
    
    @GetMapping("/createPower")
    public String showPowerForm(Model model) {
        Org organization = new Org();
        model.addAttribute("organization", organization);
        return "power-form";
    }
//
//    @GetMapping("/powers/{id}")
//    public String getPowerById(@PathVariable("id") int id, Model model) {
//        Power power = powerDAO.getPowerById(id);
//        model.addAttribute("power", power);
//        return "power-details";
//    }
//
//  

}