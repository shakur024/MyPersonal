/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.SuperHero.Dao;

import com.example.SuperHero.Dto.Org;
import java.util.List;


public interface OrgDao {

    Org getOrganizationById(int id);

    List<Org> getAllOrganization();

    Org addOrganization(Org org);

    public void updateOrganization(Org org);

    public void deleteOrganizationById(int id);
}
