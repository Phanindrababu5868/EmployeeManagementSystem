package com.Quess.FinalProject.Service;


import com.Quess.FinalProject.Exception.ResourceNotFoundException;
import com.Quess.FinalProject.Model.Organization;
import com.Quess.FinalProject.Repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrganizationServiceIml implements OrganizationService{
    @Autowired
    private OrganizationRepository organizationRepository;

    public OrganizationServiceIml(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization saveOranizationData(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public List<Organization> getAllOrganizationData() {
        return (List<Organization>) organizationRepository.findAll();
    }

    @Override
    public Organization getOrganizationById(int id) {
        return organizationRepository.findById(id).orElseThrow(() -> new com.Quess.FinalProject.Exception.ResourceNotFoundException("Organization Not found By given Id : "+id));
    }

    @Override
    public Organization updateOrganization(Organization organization, int id) {
        Organization existingDetail=organizationRepository.findById(id).orElseThrow(() -> new com.Quess.FinalProject.Exception.ResourceNotFoundException("Organization Not found By given Id : "+id));
        existingDetail.setOrganizationName(organization.getOrganizationName());
        existingDetail.setAddress(organization.getAddress());
        existingDetail.setPhoneNo(organization.getPhoneNo());
        organizationRepository.save(existingDetail);
        return existingDetail;
    }

    @Override
    public void deleteOrganization(int id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new com.Quess.FinalProject.Exception.ResourceNotFoundException("Organization Not found By given Id : "+id));
            organizationRepository.deleteById(id);

    }

}
