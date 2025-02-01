package com.empire.controller;

import com.empire.entity.RegistrationEntity;
import com.empire.service.EmployeeDashboardService;
import com.empire.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private EmployeeDashboardService employeeDashboardService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage(Model model) {
        model.addAttribute("employee", new RegistrationEntity());
        return "forgotpassword";
    }

    @PostMapping("/forgot-password")
    public String updatePassword(
            @RequestParam("email") String email,
            @RequestParam("newPassword") String newPassword,
            RedirectAttributes redirectAttributes) {
        try {
            // Attempt to find the employee by email
            RegistrationEntity employee = employeeDashboardService.getEmployeeByEmail(email);

            if (employee == null) {
                // Redirect back with an error message
                redirectAttributes.addFlashAttribute("errorMessage", "Email does not exist.");
                return "redirect:/forgot-password";
            }

            // If employee exists, update the password
            forgotPasswordService.updatePassword(employee, newPassword);
            redirectAttributes.addFlashAttribute("successMessage", "Password updated successfully!");
            return "redirect:/login"; // Redirect to the login page after successful update

        } catch (Exception ex) {
            // Catch unexpected exceptions and redirect with an error message
            redirectAttributes.addFlashAttribute("errorMessage", "Email does not exist.");
            return "redirect:/forgot-password";
        }
    }
}
