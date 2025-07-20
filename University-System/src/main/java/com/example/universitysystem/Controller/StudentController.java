package com.example.universitysystem.Controller;

import com.example.universitysystem.Api.ApiResponse;
import com.example.universitysystem.Model.StudentModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
@RestController
@RequestMapping("/api/v1/university-system")
public class StudentController {
    ArrayList<StudentModel> students = new ArrayList<>();
    static final AtomicLong idGenerator = new AtomicLong(1);

    //create student
    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody StudentModel student) {
        student.setId(idGenerator.getAndIncrement());
        students.add(student);
        return new ApiResponse("Successfully added Student ");
    }

    //get students
    @GetMapping("/get")
    public ArrayList<StudentModel> getStudent() {
        return students;
    }

    //update student
    @PutMapping("/update/{id}")
    public ApiResponse updateStudent(@RequestBody StudentModel student,@PathVariable long id) {
        for(StudentModel s:students){
            if(s.getId() == id){
                s.setName(student.getName());
                s.setAge(student.getAge());
                s.setGender(student.getGender());
                s.setGPA(student.getGPA());
                return new ApiResponse("Successfully updated Student ");
            }
        }
        return new ApiResponse("Student not found");
    }
    //deleting student
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteStudent(@PathVariable long id) {

        for(StudentModel s:students){
            if(s.getId() == id){
                students.remove(s);
                return new ApiResponse("Successfully deleted Student ");
            }
        }
        return new ApiResponse("Student not found");

    }
    // classify student
    @GetMapping("/classify/{id}")
    public ApiResponse classifyStudent(@PathVariable long id) {
        for(StudentModel s:students){
            if(s.getId() == id){
                if(s.getGPA() >= 4.75 && s.getGPA() <= 5){
                    return new ApiResponse("First Class of honor Student");
                }else if(s.getGPA() >= 4.5 && s.getGPA() <= 4.74){
                    return new ApiResponse("Second Class of honor Student");
                }else{
                    return new ApiResponse("Not Class of honor Student");
                }
            }
        }
        return new ApiResponse("Student not found");
    }
    //get student above avg
    @GetMapping("/get-student-above-average")
    public ArrayList<StudentModel> getStudentAboveAvg() {
        double sum = 0;
        ArrayList<StudentModel> studentsAboveAvg = new ArrayList<>();
        for(StudentModel s:students){
            sum += s.getGPA();
        }
        double avg = sum / students.size();
        for(StudentModel s:students){
            if(s.getGPA() > avg){
                studentsAboveAvg.add(s);
            }
        }
        return studentsAboveAvg;
    }

}
