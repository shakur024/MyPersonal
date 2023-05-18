package com.heroeducation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.heroeducation.dao.OrgDAO;
import com.heroeducation.model.Org;

@Controller
public class OrgController {

    private final OrgDAO orgDAO;

    public OrgController(OrgDAO orgDAO) {
        this.orgDAO = orgDAO;
    }

    @GetMapping("/organizations")
    public String getAllOrganizations(Model model) {
        List<Org> organizations = orgDAO.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        return "org";
    }

    @GetMapping("/createOrganizations")
    public String showOrganizationForm(Model model) {
        Org organization = new Org();
        model.addAttribute("organization", organization);
        return "organization-form";
    }

    @PostMapping("/createOrganizations")
    public String saveOrganization(@ModelAttribute("organization") Org organization) {
        orgDAO.createOrganization(organization);
        return "redirect:/organizations";
    }

    @GetMapping("/organizations/{id}/edit")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Org organization = orgDAO.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "orgUpdate";
    }

    @PostMapping("/organizations/{id}/edit")
    public String updateOrganization(@PathVariable("id") Long id, @ModelAttribute("organization") Org organization) {
        orgDAO.updateOrganization(organization);
        return "redirect:/organizations";
    }

    @GetMapping("/organizations/{id}/delete")
    public String deleteOrganization(@PathVariable("id") int id) {
        orgDAO.deleteOrganization(id);
        return "redirect:/organizations";
    }
}
