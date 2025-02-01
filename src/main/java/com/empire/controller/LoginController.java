package com.empire.controller;

import com.empire.entity.RegistrationEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.empire.entity.RegistrationEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {

        return "index";
    }

    @PostMapping("/login")
    public String handleLogin(String username, String password, Model model, RedirectAttributes redirectAttributes) {
        // Assuming you have a service to validate the credentials
        boolean isValid = validateCredentials(username, password); // You should implement this method

        if (!isValid) {
           // model.addAttribute("errorMessage", "Invalid email or password");
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid email or password");
            return "redirect:/login"; // return to the login page with the error message
        }

        // Proceed with the login logic (e.g., redirect to employee dashboard)
        return "redirect:/employee-dashboard"; // Example of successful login redirect
    }

    private boolean validateCredentials(String username, String password) {
        // Implement your logic to validate the credentials (e.g., check in the database)
        return false; // For now, returning false for demonstration
    }

}
