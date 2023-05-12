/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dao;

import com.example.SuperHero.Dto.Loc;
import com.example.SuperHero.Dto.Hero;
import java.util.List;


public interface LocDao {

    Loc getLocationById(int id);

    List<Loc> getAllLocations();

    Loc addLocation(Loc location);

    public void updateLocation(Loc location);

    public void deleteLocationById(int id);
    
    List<Loc>getAllLocationBySuperHero(Hero hero);
    
}
