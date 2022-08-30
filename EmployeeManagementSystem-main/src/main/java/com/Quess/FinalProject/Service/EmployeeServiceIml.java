package com.Quess.FinalProject.Service;

import com.Quess.FinalProject.Exception.ResourceNotFoundException;
import com.Quess.FinalProject.Model.Employee;
import com.Quess.FinalProject.Repository.Employeerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceIml implements EmployeeService {
    @Autowired
    private Employeerepository employeerepository;
    PasswordEncoder passwordEncoder;

    public EmployeeServiceIml(Employeerepository employeerepository, PasswordEncoder passwordEncoder) {
        this.employeerepository = employeerepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        String encodepass=this.passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodepass);
        return employeerepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeerepository.findAll();

    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeerepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found by given Id " + id));

    }

    @Override
    public Employee updateEmployee(Employee employee, int id) {
        Employee existingEmployee = employeerepository.findById(id).orElseThrow(() -> new com.Quess.FinalProject.Exception.ResourceNotFoundException("Employee Not found by given Id : "+id));
            existingEmployee.setName(employee.getName());
            existingEmployee.setAge(employee.getAge());
            existingEmployee.setGender(employee.getGender());
            existingEmployee.setAddress(employee.getAddress());
            existingEmployee.setPhoneNo(employee.getPhoneNo());
            existingEmployee.setSalary(employee.getSalary());
            existingEmployee.setOrganizationId(employee.getOrganizationId());

            existingEmployee.setEmail(employee.getEmail());
        String encodepass=this.passwordEncoder.encode(employee.getPassword());
            existingEmployee.setPassword(encodepass);

            employeerepository.save(existingEmployee);
            return existingEmployee;
    }

    @Override
    public void deleteEmployee(int id) {
        Employee existingemployee = employeerepository.findById(id).orElseThrow(()->new com.Quess.FinalProject.Exception.ResourceNotFoundException("Employee not found by given Id : "+id));
            employeerepository.deleteById(id);

    }
}