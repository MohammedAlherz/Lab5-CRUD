package com.example.projecttrackersystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectModel {
    private long id;
    private String title;
    private String description;
    private String status;
    private String companyName;
}
