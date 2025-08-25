package com.example.TalentTurbo.entity;


import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@Table(name = "employee")
public class newEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String name;
    private String mobile;
    private String mail;
}
