package com.example.TalentTurbo.service;

import com.example.TalentTurbo.entity.newEntity;
import com.example.TalentTurbo.repositary.newRespositary;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
public class newService {
    @Autowired
    private newRespositary respositary;
    public ResponseEntity<?>adduser(newEntity entity){
        respositary.save(entity);
        return new ResponseEntity<>("user added successfully", HttpStatus.CREATED);
    }
    public  ResponseEntity<?>displayuser(){
       return new ResponseEntity<>(respositary.findAll(),HttpStatus.OK);
    }
    public ResponseEntity<?>displaybyId(Integer id){return new ResponseEntity<>(respositary.findById(id),HttpStatus.OK);}
    public ResponseEntity<?>deleteuser(Integer id){
        newEntity n1=respositary.findById(id).orElseThrow(()->new ResourceAccessException("id not found"));
        respositary.deleteById(id);
        return new ResponseEntity<>(id+" deleted succesfully",HttpStatus.OK);
    }
    public ResponseEntity<?>updateuser(Integer id,newEntity entity){
        newEntity n1=respositary.findById(id).orElseThrow(()->new ResourceAccessException("id not exist"));
        n1.setName(entity.getName());
        n1.setMobile(entity.getMobile());
        n1.setMail(entity.getMail());
        respositary.save(n1);
        return new ResponseEntity<>("id updated successfully",HttpStatus.OK);
    }

}
