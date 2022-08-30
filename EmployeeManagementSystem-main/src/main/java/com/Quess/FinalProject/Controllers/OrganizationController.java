package com.Quess.FinalProject.Controllers;

import com.Quess.FinalProject.Exception.ResourceNotFoundException;
import com.Quess.FinalProject.Model.Organization;
import com.Quess.FinalProject.Service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/Organization/post",method = RequestMethod.POST)
    public ResponseEntity<String> saveOrganizationData(@RequestBody  @Valid Organization organization)
    {
        organizationService.saveOranizationData(organization);
        return new ResponseEntity<String>(" Organization Record created  successfully", HttpStatus.CREATED);

    }

    @RequestMapping(value = "/Organization/getall",method = RequestMethod.GET)
    public List<Organization> getAllOraganizationsData()
    {
        return organizationService.getAllOrganizationData();
    }


    @RequestMapping(value = "/Organization/getbyid/{id}",method = {RequestMethod.GET})
    public ResponseEntity<Organization> getOrganizationDataById(@PathVariable("id")int id)
    {
        return new ResponseEntity<Organization>(organizationService.getOrganizationById(id),HttpStatus.OK);

    }

    @RequestMapping(value = "/Organization/put/{id}",method = {RequestMethod.PUT})
    public ResponseEntity<String> updateOrganizationData(  @PathVariable("id")int id, @Valid @RequestBody Organization organization)
    {
        organizationService.updateOrganization(organization,id);
        return new ResponseEntity<String>("Organization Record updated successfully", HttpStatus.OK);


    }


    @RequestMapping(value="/Organization/delete/{id}",method ={RequestMethod.DELETE})
    public ResponseEntity<String> deleteOrganizationData(@PathVariable("id")int id)

    {
        organizationService.deleteOrganization(id);
        return new ResponseEntity<String>(" Organization Record deleted successfully",HttpStatus.OK);
    }
}
