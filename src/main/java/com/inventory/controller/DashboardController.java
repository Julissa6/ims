package com.inventory.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventory.repository.DeviceRepository;
import com.inventory.model.User;

@Controller
public class DashboardController {

    @Autowired
    private DeviceRepository repo;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {

        
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/";
        }

        var devices = repo.findAll();

        model.addAttribute("user", user.getUsername());
        model.addAttribute("total", devices.size());
        model.addAttribute("assigned",
                devices.stream().filter(d -> "Assigned".equals(d.getStatus())).count());
        model.addAttribute("available",
                devices.stream().filter(d -> "Available".equals(d.getStatus())).count());

        return "dashboard";
    }
}