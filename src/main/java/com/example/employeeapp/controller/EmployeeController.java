package com.example.employeeapp.controller;

import com.example.employeeapp.model.Employee;
import com.example.employeeapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/employees";
    }

    @GetMapping("/employees")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("employees", service.search(keyword));
        model.addAttribute("keyword", keyword);
        return "list";
    }

    @GetMapping("/employees/new")
    public String newForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("title", "Add employee");
        return "form";
    }

    @GetMapping("/employees/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", service.get(id));
        model.addAttribute("title", "Edit employee");
        return "form";
    }

    @PostMapping("/employees")
    public String save(@Valid @ModelAttribute("employee") Employee employee,
                       BindingResult result, Model model, RedirectAttributes ra) {
        if (!result.hasFieldErrors("email") && service.emailTaken(employee.getEmail(), employee.getId())) {
            result.rejectValue("email", "duplicate", "That email is already in use");
        }
        if (result.hasErrors()) {
            model.addAttribute("title", employee.getId() == null ? "Add employee" : "Edit employee");
            return "form";
        }
        service.save(employee);
        ra.addFlashAttribute("message", "Employee saved");
        return "redirect:/employees";
    }

    @PostMapping("/employees/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        service.delete(id);
        ra.addFlashAttribute("message", "Employee deleted");
        return "redirect:/employees";
    }
}
