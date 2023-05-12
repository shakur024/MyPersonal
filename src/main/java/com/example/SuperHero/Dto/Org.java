/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dto;

import java.util.Objects;

public class Org {
    
    int orgId;
    
    String orgName;
    
    String description;
    
    String addressInfo;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Org)) return false;
        Org org = (Org) o;
        return orgId == org.orgId && Objects.equals(orgName, org.orgName) && Objects.equals(description, org.description) && Objects.equals(addressInfo, org.addressInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgId, orgName, description, addressInfo);
    }
}
