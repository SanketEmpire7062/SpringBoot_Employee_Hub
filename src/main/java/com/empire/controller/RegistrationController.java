package com.empire.controller;

import com.empire.entity.RegistrationEntity;
import com.empire.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    @Autowired
    RegistrationService regservice;

    @GetMapping("/registration")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new RegistrationEntity());
        return "registration";
    }

    @PostMapping("/save")
    public String saveEmployee(RegistrationEntity regentity, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong. Please try again.");
            return "redirect:/registration"; // Redirect to show error
        }

        // Check if email already exists
        if (regservice.isEmailAlreadyPresent(regentity.getEmail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email already exists. Please try again with a different email.");
            return "redirect:/registration"; // Redirect back with error message
        }


        regservice.saveEmployee(regentity);
        redirectAttributes.addFlashAttribute("accountCreated", "Account created successfully.");
        return "redirect:/login";


    }

}