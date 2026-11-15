package com.example.employeeapp.config;

import com.example.employeeapp.model.Employee;
import com.example.employeeapp.repository.EmployeeRepository;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seed(EmployeeRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Employee("Ankita N", "ankita@example.com", "DevOps", new BigDecimal("1200000")));
                repo.save(new Employee("Ravi Kumar", "ravi@example.com", "Engineering", new BigDecimal("1500000")));
                repo.save(new Employee("Meena S", "meena@example.com", "HR", new BigDecimal("900000")));
            }
        };
    }
}
