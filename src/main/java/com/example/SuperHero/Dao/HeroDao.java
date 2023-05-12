/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dao;

import com.example.SuperHero.Dto.Hero;
import com.example.SuperHero.Dto.Loc;
import com.example.SuperHero.Dto.Org;

import java.util.List;

public interface HeroDao {

    Hero getSuperHeroById(int id);

    List<Hero> getAllSuperHero();

    Hero addSuperHero(Hero superHero);

    public void updateSuperHero(Hero superHero);

    public void deleteSuperHeroById(int id);

    List<Hero> getAllSuperHeroByLocation(Loc location);

    List<Hero> getAllSuperHeroByOrganization(Org org);
    
}
