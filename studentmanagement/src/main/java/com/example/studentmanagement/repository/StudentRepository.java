
package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository
        extends JpaRepository<Student, Integer> {

    List<Student> findByEmail(String email);

    List<Student> findByCourseName(String courseName);
}
