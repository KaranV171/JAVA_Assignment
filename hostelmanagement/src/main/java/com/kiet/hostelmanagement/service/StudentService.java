
package com.kiet.hostelmanagement.service;

import com.kiet.hostelmanagement.entity.Student;
import com.kiet.hostelmanagement.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 1. Add Student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // 2. Get All Students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 3. Get Student By ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // 4. Update Student
    public Optional<Student> updateStudent(Long id, Student newStudent) {

        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {

            Student student = optionalStudent.get();

            student.setHostelName(newStudent.getHostelName());
            student.setRoomNumber(newStudent.getRoomNumber());
            student.setFee(newStudent.getFee());

            return Optional.of(studentRepository.save(student));
        }

        return Optional.empty();
    }

    // 5. Delete Student
    public boolean deleteStudent(Long id) {

        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
