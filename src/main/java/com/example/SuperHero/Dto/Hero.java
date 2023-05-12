/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dto;

import java.util.List;
import java.util.Objects;


public class Hero {
    
    int heroId;
    
    String heroes;
    
    String description;
    
    String powers;
    
    List<Sight> sights;
    
    List<Org> org;

    List<Loc> loc;

    public List<Loc> getLoc() {
        return loc;
    }

    public void setLoc(List<Loc> loc) {
        this.loc = loc;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getHeroes() {
        return heroes;
    }

    public void setHeroes(String heroes) {
        this.heroes = heroes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    public List<Sight> getSights() {
        return sights;
    }

    public void setSights(List<Sight> sights) {
        this.sights = sights;
    }

    public List<Org> getOrganizations() {
        return org;
    }

    public void setOrganizations(List<Org> orgs) {
        this.org = orgs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.heroId;
        hash = 53 * hash + Objects.hashCode(this.heroes);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.powers);
        hash = 53 * hash + Objects.hashCode(this.sights);
        hash = 53 * hash + Objects.hashCode(this.org);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hero other = (Hero) obj;
        if (this.heroId != other.heroId) {
            return false;
        }
        if (!Objects.equals(this.heroes, other.heroes)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.powers, other.powers)) {
            return false;
        }
        if (!Objects.equals(this.sights, other.sights)) {
            return false;
        }
        if (!Objects.equals(this.org, other.org)) {
            return false;
        }
        return true;
    }
    
    
    
}
