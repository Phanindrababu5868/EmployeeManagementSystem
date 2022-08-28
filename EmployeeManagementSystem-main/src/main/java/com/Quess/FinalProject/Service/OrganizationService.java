package com.Quess.FinalProject.Service;


import com.Quess.FinalProject.Model.Organization;

import java.util.List;

public interface OrganizationService {
    Organization saveOranizationData(Organization organization);
    List<Organization> getAllOrganizationData();
    Organization getOrganizationById(int id);
    Organization updateOrganization(Organization organization, int id);
    void deleteOrganization(int id);
}
