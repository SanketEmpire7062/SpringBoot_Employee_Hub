package com.empire.controller;

import com.empire.entity.RegistrationEntity;
import com.empire.service.AdminDashboardService;
import com.empire.service.RegistrationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminDashboardController {

    @Autowired
    AdminDashboardService adminDashboardService;

    @Autowired
    RegistrationService regservice;

    @GetMapping("/admindashboard")
    public String employeeDashboard(Model model, Principal principal) {
        String email = principal.getName();
        RegistrationEntity logInEmployee = adminDashboardService.getloggedInEmployee(email);
        List<RegistrationEntity> employee = adminDashboardService.getAllEmployee();
        model.addAttribute("employee", employee);
        model.addAttribute("logInEmployee", logInEmployee);
        return "admindashboard";
    }

    // For changing role
    @PostMapping("/change-role/{email}")
    public String changeRole(@PathVariable String email, Model model) {
        RegistrationEntity regentity = adminDashboardService.getEmployeeById(email);
        if (regentity != null) {
            if ("USER".equals(regentity.getRole())) {
                regentity.setRole("ADMIN");
            } else {
                regentity.setRole("USER");
            }
            adminDashboardService.saveRoleEmployee(regentity); // Saves with password encoding if necessary
        }
        return "redirect:/admindashboard";
    }

    @Transactional
    @PostMapping("/delete-employee/{email}")
    public String deleteEmployeeByEmail(@PathVariable String email) {
        adminDashboardService.deleteEmployeeByEmail(email);
        return "redirect:/admindashboard";
    }
}
