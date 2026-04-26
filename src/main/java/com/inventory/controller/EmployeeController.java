package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.inventory.repository.EmployeeRepository;
import com.inventory.model.Employee;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

   
    @GetMapping("/employees")
    public String list(Model model) {
        model.addAttribute("employees", repo.findAll());
        return "employees";
    }

    @GetMapping("/add-employee")
    public String add(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    
    @PostMapping("/save-employee")
    public String save(Employee e) {

        if (e.getName() == null || e.getName().isEmpty()) {
            return "redirect:/add-employee";
        }

        repo.save(e);
        return "redirect:/employees";
    }

  
    @GetMapping("/edit-employee/{id}")
    public String editEmp(@PathVariable Long id, Model model) {

        Employee emp = repo.findById(id).orElse(null);

        if (emp == null) {
            return "redirect:/employees";
        }

        model.addAttribute("employee", emp);
        return "edit-employee";
    }

    @PostMapping("/update-employee")
    public String updateEmp(Employee e) {

        if (e.getId() == null) {
            return "redirect:/employees";
        }

        Employee existing = repo.findById(e.getId()).orElse(null);

        if (existing != null) {
            existing.setName(e.getName());
           
            existing.setDepartment(e.getDepartment());

            repo.save(existing);
        }

        return "redirect:/employees";
    }

  
    @GetMapping("/delete-employee/{id}")
    public String delete(@PathVariable Long id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
        }

        return "redirect:/employees";
    }
}