package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.inventory.repository.*;
import com.inventory.model.*;

import java.util.Date;

@Controller
public class AssignmentController {

    @Autowired
    private AssignmentRepository assignRepo;

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private AuditRepository auditRepo;

    
    @GetMapping("/assign-device")
    public String assignPage(Model model) {

        model.addAttribute("assignment", new Assignment());
        model.addAttribute("employees", employeeRepo.findAll());
        model.addAttribute("devices", deviceRepo.findAll());

        return "assign-device";
    }

   
    @PostMapping("/assign-save")
    public String assignSave(Assignment a) {

        if (a.getDeviceId() == null || a.getEmployeeId() == null) {
            return "redirect:/assign-device";
        }

        a.setAssignedDate(new Date());
        assignRepo.save(a);

        Device d = deviceRepo.findById(a.getDeviceId()).orElse(null);

        if (d != null) {
            d.setStatus("Assigned");
            deviceRepo.save(d);
        }

        AuditLog log = new AuditLog();
        log.setAction("Device Assigned");
        log.setUserId(1L);
        log.setDate(new Date());

        auditRepo.save(log);

        return "redirect:/dashboard";
    }

    @GetMapping("/return-device/{id}")
    public String returnDevice(@PathVariable Long id) {

        Assignment a = assignRepo.findById(id).orElse(null);

        if (a != null) {

            Device d = deviceRepo.findById(a.getDeviceId()).orElse(null);

            if (d != null) {
                d.setStatus("Available");
                deviceRepo.save(d);
            }

            assignRepo.deleteById(id);

            AuditLog log = new AuditLog();
            log.setAction("Device Returned");
            log.setUserId(1L);
            log.setDate(new Date());

            auditRepo.save(log);
        }

        return "redirect:/devices";
    }
}