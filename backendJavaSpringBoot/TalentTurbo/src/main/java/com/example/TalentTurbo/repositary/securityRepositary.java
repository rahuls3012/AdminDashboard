package com.example.TalentTurbo.repositary;

import com.example.TalentTurbo.entity.securityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface securityRepositary extends JpaRepository<securityEntity ,Integer> {
    Optional<securityEntity>findByMail(String mail);
}
