package com.example.TalentTurbo.controller;

import com.example.TalentTurbo.entity.newEntity;
import com.example.TalentTurbo.service.newService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ace")
@Slf4j
public class newController {
    @Autowired
    public newService service;
    @PostMapping("/adduser")
     public ResponseEntity<?>addDetails(@RequestBody newEntity entity){
        return service.adduser(entity);
    }
    @GetMapping("/showuser")
    public ResponseEntity<?>display(){
        return service.displayuser();
    }
    @GetMapping("/getuser")
    public ResponseEntity<?>displayUser(@RequestParam Integer id){return service.displaybyId(id);}
    @DeleteMapping("/delete")
    public ResponseEntity<?>delete(@RequestParam Integer id){
        return service.deleteuser(id);
    }
    @PutMapping("/update")
    public ResponseEntity<?>update(@RequestParam Integer id,@RequestBody newEntity entity){
        return service.updateuser(id,entity);
    }

}
