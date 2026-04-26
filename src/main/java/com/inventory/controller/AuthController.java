package com.inventory.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.inventory.repository.UserRepository;
import com.inventory.model.User;

@Controller
public class AuthController {

    @Autowired
    private UserRepository repo;

    // show login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // handle login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User u = repo.findByUsername(username);

        if (u != null && u.getPassword().equals(password)) {

            session.setAttribute("loggedUser", u);

            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    // logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}