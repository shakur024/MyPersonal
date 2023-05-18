package com.heroeducation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heroeducation.dao.HeroDAO;
import com.heroeducation.model.Hero;
@Controller
public class HeroController {
	private final HeroDAO heroDAO; // Inject the HeroDAO dependency

    public HeroController(HeroDAO heroDAO) {
        this.heroDAO = heroDAO;
    }
    
   

    @GetMapping("/heroes")
    public String getAllHeroes(Model model) {
        List<Hero> heroes = heroDAO.getAllHeroes();
        model.addAttribute("heroes", heroes);
        return "hero";
    }

    @GetMapping("/heroes/{id}")
    public String getHeroById(@PathVariable int id, Model model) {
        Hero hero = heroDAO.getHeroById(id);
        model.addAttribute("hero", hero);
        return "hero-details";
    }

    @GetMapping("/createHero")
    public String showCreateForm(Model model) {
        model.addAttribute("hero", new Hero());
        return "hero-form";
    }

    @PostMapping("/createHero")
    public String createHero(@ModelAttribute("hero") Hero hero) {
        heroDAO.createHero(hero);
        return "redirect:/heroes";
    }

    @GetMapping("/heroes/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Hero hero = heroDAO.getHeroById(id);
        model.addAttribute("hero", hero);
        return "heroUpdate";
    }

    @PostMapping("/heroes/{id}/edit")
    public String updateHero(@ModelAttribute("hero") Hero hero) {
        heroDAO.updateHero(hero);
        return "redirect:/heroes";
    }

    @GetMapping("/heroes/{id}/delete")
    public String deleteHero(@PathVariable int id) {
    	System.out.println(id);
    	System.out.println("hello from this");
        heroDAO.deleteHero(id);
        return "redirect:/heroes";
    }

}
