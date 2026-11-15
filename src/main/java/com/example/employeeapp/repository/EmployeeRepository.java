package com.example.employeeapp.repository;

import com.example.employeeapp.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(String name, String department);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
