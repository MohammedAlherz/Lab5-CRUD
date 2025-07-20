package com.example.projecttrackersystem.Controller;

import com.example.projecttrackersystem.Api.ApiResponse;
import com.example.projecttrackersystem.Model.ProjectModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/project-tracker-system")
public class ProjectController {
    ArrayList<ProjectModel> projects = new ArrayList<>();
    static final AtomicLong idGenerator = new AtomicLong(1);

    //adding project
    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody ProjectModel project) {
        project.setId(idGenerator.getAndIncrement());
        projects.add(project);
        return new ApiResponse("Successfully added project");
    }

    //get project
    @GetMapping("/get")
    public ArrayList<ProjectModel> getProjects() {
        return projects;
    }

    //update project
    @PutMapping("/update/{id}")
    public ApiResponse updateProject(@RequestBody ProjectModel project,@PathVariable long id) {
        for(ProjectModel p : projects) {
            if(p.getId() == id) {
                p.setTitle(project.getTitle());
                p.setDescription(project.getDescription());
                p.setStatus(project.getStatus());
                p.setCompanyName(project.getCompanyName());
                return new ApiResponse("Successfully updated project");
            }
        }
        return new ApiResponse("Project not found");
    }

    //delete project
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteProject(@PathVariable long id) {
        for(ProjectModel p : projects) {
            if(p.getId() == id) {
                projects.remove(p);
                return new ApiResponse("Successfully deleted project");
            }
        }
        return new ApiResponse("Project not found");
    }

    //update status
    @PutMapping("/change-status/{id}")
    public ApiResponse changeStatus(@PathVariable long id) {
        for (ProjectModel p : projects) {
            if (p.getId() == id) {
                String currentStatus = p.getStatus();
                p.setStatus(currentStatus.equalsIgnoreCase("done") ? "not done" : "done");
                return new ApiResponse("Project status changed successfully!");
            }
        }
        return new ApiResponse("Project not found!");
    }

    // Search project by Title
    @GetMapping("/search/{title}")
    public Object searchTask(@PathVariable String title) {
        for (ProjectModel p : projects) {
            if (Objects.equals(p.getTitle(), title)) {
                return p;  // return full project info
            }
        }
        return new ApiResponse("project not found!");
    }

    //get all projects that are from same company
    @GetMapping("/projects/{companyName}")
    public ArrayList<ProjectModel> getProjectSameCompany(@PathVariable String companyName) {
        ArrayList<ProjectModel> projectsSameCompany = new ArrayList<>();
        for (ProjectModel p : projects) {
            if (p.getCompanyName().equals(companyName)) {
                projectsSameCompany.add(p);
            }
        }
        return projectsSameCompany;
    }
}
