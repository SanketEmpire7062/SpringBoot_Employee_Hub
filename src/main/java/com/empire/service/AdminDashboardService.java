package com.empire.service;

import com.empire.entity.RegistrationEntity;
import com.empire.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDashboardService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    @Lazy
    BCryptPasswordEncoder passwordEncoder;

    public RegistrationEntity getloggedInEmployee(String email) {
        return registrationRepository.findByEmail(email);
    }

    public List<RegistrationEntity> getAllEmployee() {
        return registrationRepository.findAll();
    }

    public RegistrationEntity getEmployeeById(String email) {
        return registrationRepository.findByEmail(email);
    }

    public void deleteEmployeeByEmail(String email) {
        registrationRepository.deleteByEmail(email);
    }

    // Method to handle password encoding
    private void encodePasswordIfNeeded(RegistrationEntity regentity) {
        if (regentity.getPassword() != null && !regentity.getPassword().startsWith("$2a$")) {
            String encodedPassword = passwordEncoder.encode(regentity.getPassword());
            regentity.setPassword(encodedPassword);
        }
    }


    public void saveRoleEmployee(RegistrationEntity regentity) {


        if (regentity.getPassword() != null && !regentity.getPassword().startsWith("$2a$")) {
            String encodedPassword = passwordEncoder.encode(regentity.getPassword());
            regentity.setPassword(encodedPassword);
        }


        registrationRepository.save(regentity);
        System.out.println("Employee saved ::" + regentity.toString());
    }
}
