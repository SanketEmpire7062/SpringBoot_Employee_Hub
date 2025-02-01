package com.empire.service;

import com.empire.entity.RegistrationEntity;
import com.empire.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDashboardService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public RegistrationEntity getEmployeeByEmail(String email) {
        RegistrationEntity employee = registrationRepository.findByEmail(email);
        if (employee == null) {
            throw new IllegalArgumentException("No employee found with email: " + email);
        }
        return employee;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void saveUpdatdEmployee(RegistrationEntity regentity) {


        if (regentity.getPassword() != null && !regentity.getPassword().startsWith("$2a$")) {
            String encodedPassword = passwordEncoder.encode(regentity.getPassword());
            regentity.setPassword(encodedPassword);
        }


        registrationRepository.save(regentity);
        System.out.println("Employee saved ::" + regentity.toString());
    }

    public void deleteEmployee(String email) {
        registrationRepository.deleteByEmail(email);
    }
}
