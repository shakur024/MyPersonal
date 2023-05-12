/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dto;

import java.time.LocalDate;
import java.util.Objects;


public class Sight {

    int sightId;

    LocalDate sightDate;

    Loc location;

    public int getSightId() {
        return sightId;
    }

    public void setSightId(int sightId) {
        this.sightId = sightId;
    }

    public LocalDate getSightDate() {
        return sightDate;
    }

    public void setSightDate(LocalDate sightDate) {
        this.sightDate = sightDate;
    }

    public Loc getLocation() {
        return location;
    }

    public void setLocation(Loc location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.sightId;
        hash = 53 * hash + Objects.hashCode(this.sightDate);
        hash = 53 * hash + Objects.hashCode(this.location);
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
        final Sight other = (Sight) obj;
        if (this.sightId != other.sightId) {
            return false;
        }
        if (!Objects.equals(this.sightDate, other.sightDate)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }

}
