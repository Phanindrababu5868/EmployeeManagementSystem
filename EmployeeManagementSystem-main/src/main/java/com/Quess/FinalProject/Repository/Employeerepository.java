package com.Quess.FinalProject.Repository;

import com.Quess.FinalProject.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Employeerepository extends JpaRepository<Employee,Integer> {
    Employee findByEmail(String username);
}
