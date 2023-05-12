/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dto;

import java.util.Objects;


public class Loc {
    
   int locId;
   
   String LocName;
   
   String description;
   
   String addressInfo;
   
   double latitude;
   
   double longitude;


    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
    }

    public String getLocName() {
        return LocName;
    }

    public void setLocName(String locName) {
        LocName = locName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loc)) return false;
        Loc loc = (Loc) o;
        return locId == loc.locId && Double.compare(loc.latitude, latitude) == 0 && Double.compare(loc.longitude, longitude) == 0 && Objects.equals(LocName, loc.LocName) && Objects.equals(description, loc.description) && Objects.equals(addressInfo, loc.addressInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locId, LocName, description, addressInfo, latitude, longitude);
    }
}
