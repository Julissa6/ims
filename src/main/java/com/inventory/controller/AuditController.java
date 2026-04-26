package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.inventory.repository.AuditRepository;

@Controller
public class AuditController {

    @Autowired
    private AuditRepository repo;

    @GetMapping("/audit")
    public String view(Model model) {
        model.addAttribute("logs", repo.findAll());
        return "audit";
    }
}