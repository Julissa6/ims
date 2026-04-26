package com.inventory.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.model.User;
import com.inventory.repository.UserRepository;

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
                         HttpSession session) {

        User u = repo.findByUsername(username);

        if (u != null && u.getPassword() != null
                && u.getPassword().equals(password)) {

            session.setAttribute("loggedUser", u);
            return "redirect:/dashboard";
        }

        return "redirect:/login?error";
    }

    // logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}