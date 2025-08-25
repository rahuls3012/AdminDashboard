package com.example.TalentTurbo.controller;

import com.example.TalentTurbo.entity.securityEntity;
import com.example.TalentTurbo.repositary.securityRepositary;
import com.example.TalentTurbo.service.securityService;
import com.example.TalentTurbo.utils.Jwtutil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class securityController {
    @Autowired
    private securityRepositary SR;
    @Autowired
    private securityService SS;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Jwtutil jwtutil;


    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody Map<String,String>body ){
        String mail=body.get("mail");
        String password=passwordEncoder.encode(body.get("password"));
        if(SR.findByMail(mail).isPresent()){
            return new ResponseEntity<>("email already present", HttpStatus.UNAUTHORIZED);
        }

            SS.create(securityEntity.builder().mail(mail).password(password).build());
            return new ResponseEntity<>("registered successfully", HttpStatus.CREATED);


    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody Map<String,String> body){
        String mail=body.get("mail");
        String password=body.get("password");
        if(SR.findByMail(mail).isEmpty()){
            return new ResponseEntity<>("user not found",HttpStatus.UNAUTHORIZED);
        }

        securityEntity SEntity=SR.findByMail(mail).get();
        if (!passwordEncoder.matches(password,SEntity.getPassword())){
            return new ResponseEntity<>("password not matched",HttpStatus.FORBIDDEN);
        }
       try {
           String token = jwtutil.jwtTokenGeneration(mail);
           return ResponseEntity.ok(Map.of("token", token));
       }catch (Exception e){
           e.printStackTrace();
           return new ResponseEntity<>("token failed",HttpStatus.UNAUTHORIZED);
       }
    }
}
