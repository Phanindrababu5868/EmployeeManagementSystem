package com.Quess.FinalProject.Controllers;

import com.Quess.FinalProject.Exception.ResourceNotFoundException;
import com.Quess.FinalProject.Model.Employee;
import com.Quess.FinalProject.Security.userSecurity;
import com.Quess.FinalProject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

//import javax.validation.Valid;
import javax.validation.Valid;
import java.lang.module.ResolutionException;
import java.security.Principal;
import java.util.List;

@RestController

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/Employee/post",method = RequestMethod.POST)
    public ResponseEntity<String> saveEmployee( @RequestBody  @Valid Employee employee)
    {
        try{employeeService.saveEmployee(employee);
        return new ResponseEntity<String>("Employee record Created successfully", HttpStatus.CREATED);}
        catch (Exception e){throw new com.Quess.FinalProject.Exception.ResourceNotFoundException("Enter all details Correctly");}
    }


    @RequestMapping(value = "/Employee/getall",method = RequestMethod.GET)
    public List<Employee> getAllEmployees()
    {
        return employeeService.getAllEmployees();
    }


    @RequestMapping(value = "/Employee/get/{id}",method = {RequestMethod.GET})
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id)
    {
        try{
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            Employee employee=employeeService.getEmployeeById(id);

            if(employee.getEmail().equals(authentication.getName())){

                employee=employeeService.getEmployeeById(id);
                return new ResponseEntity<Employee>(employee,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("Id not found");
        }
    }
    @GetMapping(value = "/Employee/getbyid/{id}")
    public ResponseEntity<Employee>getEmployeById(@PathVariable("id") int id){

        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }




    @RequestMapping(value = "/Employee/put/{id}",method={RequestMethod.PUT})
    public ResponseEntity<String> updateEmployee( @PathVariable("id") int id, @Valid @RequestBody Employee employee)
    {
        employeeService.updateEmployee(employee,id);
        return new ResponseEntity<String>("Employee record updated successfully",HttpStatus.OK);

    }


    @RequestMapping(value = "/Employee/delete/{id}",method = {RequestMethod.DELETE})
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id)
    {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee record deleted successfully",HttpStatus.OK);


    }
    @RequestMapping(value = "/Employee/user/{id}",method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeeByIde(@PathVariable("id")int id)
    {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);

    }


}