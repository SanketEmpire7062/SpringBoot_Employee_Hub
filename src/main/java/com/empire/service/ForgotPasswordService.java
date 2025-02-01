package com.empire.service;

import com.empire.entity.RegistrationEntity;
import com.empire.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void updatePassword(RegistrationEntity employee, String newPassword) {
        // Ensure password encoding is handled properly
        if (newPassword != null && !newPassword.startsWith("$2a$")) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            employee.setPassword(encodedPassword);
        }
        saveUpdatedPassword(employee);
    }

    public void saveUpdatedPassword(RegistrationEntity regentity) {
        // Save the updated employee details in the repository
        registrationRepository.save(regentity);
    }
}
