
package com.kiet.hostelmanagement.repository;

import com.kiet.hostelmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
