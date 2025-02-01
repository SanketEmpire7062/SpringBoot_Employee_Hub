package com.empire.controller;

import com.empire.entity.RegistrationEntity;
import com.empire.service.AdminDashboardService;
import com.empire.service.EmployeeDashboardService;
import com.empire.service.RegistrationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeDashboardController {

    @Autowired
    private EmployeeDashboardService employeeDashboardService;

    @Autowired
    private AdminDashboardService adminDashboardService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/employeedashboard")
    public String showEmployeeDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = auth.getName();

        RegistrationEntity employee = employeeDashboardService.getEmployeeByEmail(loggedInEmail);

        if (employee == null) {
            model.addAttribute("error", "Employee data not found for the logged-in user.");
            return "error"; // Redirect to a generic error page
        }

        model.addAttribute("employee", employee);
        return "employeedashboard"; // Dashboard Thymeleaf template
    }

    @GetMapping("/updateemployee")
    public String showUpdateForm(@RequestParam("email") String email, Model model) {
        RegistrationEntity employee = employeeDashboardService.getEmployeeByEmail(email);

        if (employee == null) {
            throw new RuntimeException("Employee not found with email: " + email);
        }

        model.addAttribute("employee", employee);
        return "updateemployee"; // Thymeleaf template for updating employee
    }

    @PostMapping("/updateemployee")
    public String updateEmployee(RegistrationEntity updatedEmployee, String newPassword) {
        RegistrationEntity existingEmployee = employeeDashboardService.getEmployeeByEmail(updatedEmployee.getEmail());

        if (existingEmployee == null) {
            throw new RuntimeException("Employee not found with email: " + updatedEmployee.getEmail());
        }

        // Update only non-null fields
        if (updatedEmployee.getName() != null) existingEmployee.setName(updatedEmployee.getName());
        if (updatedEmployee.getMobile() != null) existingEmployee.setMobile(updatedEmployee.getMobile());
        if (updatedEmployee.getDob() != null) existingEmployee.setDob(updatedEmployee.getDob());
        if (updatedEmployee.getDateOfJoining() != null)
            existingEmployee.setDateOfJoining(updatedEmployee.getDateOfJoining());

        // Encode and update password only if provided


        employeeDashboardService.saveUpdatdEmployee(existingEmployee);
        return "redirect:/employeedashboard?message=Information updated successfully!";
    }

    @Transactional
    @PostMapping("/delete-data/{email}")
    public String deleteEmployee(@PathVariable String email) {
        employeeDashboardService.deleteEmployee(email);
        return "index";
    }
}
