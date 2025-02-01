package com.empire.repository;

import com.empire.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long> {

    RegistrationEntity findByEmail(String email);

    public void deleteByEmail(String email);


}
