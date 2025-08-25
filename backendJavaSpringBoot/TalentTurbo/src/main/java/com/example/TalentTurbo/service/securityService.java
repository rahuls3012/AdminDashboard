package com.example.TalentTurbo.service;

import com.example.TalentTurbo.entity.securityEntity;
import com.example.TalentTurbo.repositary.securityRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class securityService {
    @Autowired
    private securityRepositary sR;
    public ResponseEntity<?>create(securityEntity sE){
        sR.save(sE);
        return new ResponseEntity<>("new admin added",HttpStatus.CREATED);
    }

}
