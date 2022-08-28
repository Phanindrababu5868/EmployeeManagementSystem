package com.Quess.FinalProject.Service;

import com.Quess.FinalProject.Model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee updateEmployee(Employee employee, int id);

    void deleteEmployee(int id);
}
