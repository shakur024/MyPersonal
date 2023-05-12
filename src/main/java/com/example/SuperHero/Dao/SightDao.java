/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dao;

import com.example.SuperHero.Dto.Loc;
import com.example.SuperHero.Dto.Sight;
import java.time.LocalDate;
import java.util.List;


public interface SightDao {

    Sight getSightingById(int id);

    List<Sight> getAllSightings();

    Sight addSighting(Sight sighting);

    public void updateSighting(Sight sighting);

    public void deleteSightingById(int id);
    
    List<Sight> getAllSightingByDate(LocalDate date);
    
    List<Sight> getAllSightingByLocation(Loc location);
    

}
