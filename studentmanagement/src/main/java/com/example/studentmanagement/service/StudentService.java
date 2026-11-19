
package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }


    public Student addStudent(Student student) {

        validateScores(student);

        if (repo.existsById(student.getStudentId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student ID already exists"
            );
        }

        return repo.save(student);
    }


    public List<Student> getAllStudents() {
        return repo.findAll();
    }


    public Student getStudentById(int id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));
    }


    public Student updateStudent(int id, Student updated) {

        Student student = getStudentById(id);

        validateScores(updated);

        student.setProgrammingScore(
                updated.getProgrammingScore());

        student.setMathematicsScore(
                updated.getMathematicsScore());

        student.setTechnicalSkills(
                updated.getTechnicalSkills());

        return repo.save(student);
    }

    public void deleteStudent(int id) {

        Student student = getStudentById(id);

        repo.delete(student);
    }


    public List<Student> getMeritList() {

        List<Student> students = repo.findAll();

        students.sort((a, b) ->
                Double.compare(b.getAverage(), a.getAverage()));

        return students;
    }


    public List<Student> searchByEmail(String email) {
        return repo.findByEmail(email);
    }


    public List<Student> searchByCourse(String course) {
        return repo.findByCourseName(course);
    }


    private void validateScores(Student student) {

        if (student.getProgrammingScore() < 0 ||
                student.getProgrammingScore() > 100 ||
                student.getMathematicsScore() < 0 ||
                student.getMathematicsScore() > 100) {

            throw new RuntimeException(
                    "Scores must be between 0 and 100");
        }
    }
}
