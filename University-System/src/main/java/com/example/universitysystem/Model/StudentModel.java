package com.example.universitysystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentModel {
    private long id;
    private String name;
    private int age;
    private String gender;
    private double GPA;

}
