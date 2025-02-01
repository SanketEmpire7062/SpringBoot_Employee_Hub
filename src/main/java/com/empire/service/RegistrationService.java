package com.empire.service;

import com.empire.entity.RegistrationEntity;
import com.empire.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RegistrationService implements UserDetailsService {

    @Autowired
    RegistrationRepository regrepo;

    public RegistrationService(RegistrationRepository regrepo) {
        this.regrepo = regrepo;
    }

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    //logic to save data in database
    public void saveEmployee(RegistrationEntity regentity) {

        if (regrepo.findByEmail(regentity.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Assign default role if not already set (optional, as it is already handled in the entity constructor)
        if (regentity.getRole() == null) {
            regentity.setRole("USER");
        }

        if (regentity.getPassword() != null && !regentity.getPassword().startsWith("$2a$")) {
            String encodedPassword = passwordEncoder.encode(regentity.getPassword());
            regentity.setPassword(encodedPassword);
        }


        regrepo.save(regentity);

    }

    //get the email
    public RegistrationEntity getEmployeeByEmail(String email) {
        return regrepo.findByEmail(email);

    }

    public boolean isEmailAlreadyPresent(String email) {
        return regrepo.findByEmail(email) != null;
    }

    //getting the user name and password
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        RegistrationEntity regentity = regrepo.findByEmail(email);
        //System.out.println("password is sjnsjdns::" + regentity.getPassword());

        if (regentity == null) {

            throw new UsernameNotFoundException("User not found");
        }
        //check the user role based on email id or password
        else {
            return new org.springframework.security.core.userdetails.User(

                    regentity.getEmail(), regentity.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_" + regentity.getRole())));
        }
    }
}
