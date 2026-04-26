package com.inventory.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventory.model.Device;
import com.inventory.model.User;
import com.inventory.repository.DeviceRepository;

@Controller
public class DashboardController {

    @Autowired
    private DeviceRepository repo;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {

        // MUST match login session attribute
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        List<Device> devices = repo.findAll();

        model.addAttribute("user", user.getUsername());
        model.addAttribute("total", devices.size());
        model.addAttribute("assigned",
                devices.stream().filter(d -> "Assigned".equals(d.getStatus())).count());
        model.addAttribute("available",
                devices.stream().filter(d -> "Available".equals(d.getStatus())).count());

        return "dashboard";
    }
}