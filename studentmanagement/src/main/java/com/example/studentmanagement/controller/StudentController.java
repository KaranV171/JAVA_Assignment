
package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }


    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }


    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }


    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return service.getStudentById(id);
    }

    @GetMapping("/email/{email}")
    public List<Student> searchEmail(@PathVariable String email) {
        return service.searchByEmail(email);
    }


    @GetMapping("/course/{course}")
    public List<Student> searchCourse(@PathVariable String course) {
        return service.searchByCourse(course);
    }


    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable int id,
            @RequestBody Student student) {

        return service.updateStudent(id, student);
    }


    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {

        service.deleteStudent(id);

        return "Student deleted successfully";
    }


    @GetMapping("/merit")
    public List<Student> meritList() {
        return service.getMeritList();
    }
}
