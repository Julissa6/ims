package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.inventory.repository.DeviceRepository;
import com.inventory.model.Device;

import java.util.List;

@Controller
public class DeviceController {

    @Autowired
    private DeviceRepository repo;

    
    @GetMapping("/devices")
    public String list(Model model) {
        model.addAttribute("devices", repo.findAll());
        return "devices";
    }

   
    @GetMapping("/add-device")
    public String add(Model model) {
        model.addAttribute("device", new Device());
        return "add-device";
    }

   
    @PostMapping("/save-device")
    public String save(@ModelAttribute Device d) {

        if (d.getSerialNumber() == null || d.getSerialNumber().isEmpty()) {
            return "redirect:/add-device";
        }

        d.setStatus("Available");
        repo.save(d);

        return "redirect:/devices";
    }

    // EDIT PAGE
    @GetMapping("/edit-device/{id}")
    public String editDevice(@PathVariable Long id, Model model) {

        Device device = repo.findById(id).orElse(null);

        if (device == null) {
            return "redirect:/devices";
        }

        model.addAttribute("device", device);
        return "edit-device";
    }

    @PostMapping("/update-device")
    public String updateDevice(@ModelAttribute Device d) {

        if (d.getId() == null) {
            return "redirect:/devices";
        }

        Device existing = repo.findById(d.getId()).orElse(null);

        if (existing == null) {
            return "redirect:/devices";
        }

        existing.setName(d.getName());
        existing.setType(d.getType());
        existing.setSerialNumber(d.getSerialNumber());
        existing.setCondition(d.getCondition());

        repo.save(existing);

        return "redirect:/devices";
    }

  
    @GetMapping("/delete-device/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/devices";
    }

 
    @GetMapping("/search-device")
    public String search(@RequestParam String keyword, Model model) {

        List<Device> devices = repo.findAll().stream()
                .filter(d -> d.getName() != null &&
                        d.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        model.addAttribute("devices", devices);
        return "devices";
    }
}