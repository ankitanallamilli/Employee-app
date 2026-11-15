package com.example.employeeapp.service;

import com.example.employeeapp.model.Employee;
import com.example.employeeapp.repository.EmployeeRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<Employee> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return repo.findAll(Sort.by("name"));
        }
        String k = keyword.trim();
        return repo.findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(k, k);
    }

    @Transactional(readOnly = true)
    public Employee get(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Employee " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public boolean emailTaken(String email, Long id) {
        return id == null ? repo.existsByEmail(email) : repo.existsByEmailAndIdNot(email, id);
    }

    public Employee save(Employee e) {
        return repo.save(e);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
