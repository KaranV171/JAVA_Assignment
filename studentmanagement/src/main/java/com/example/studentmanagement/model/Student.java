
package com.example.studentmanagement.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private int studentId;

    private String studentName;
    private String email;
    private String courseName;

    private double programmingScore;
    private double mathematicsScore;

    @ElementCollection
    @CollectionTable(
            name = "student_skills",
            joinColumns = @JoinColumn(name = "student_id")
    )
    @Column(name = "skill")
    private List<String> technicalSkills = new ArrayList<>();

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(int studentId, String studentName,
                   String email, String courseName,
                   double programmingScore,
                   double mathematicsScore,
                   List<String> technicalSkills) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.courseName = courseName;
        this.programmingScore = programmingScore;
        this.mathematicsScore = mathematicsScore;
        this.technicalSkills = technicalSkills;
    }

    // Getters and setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getProgrammingScore() {
        return programmingScore;
    }

    public void setProgrammingScore(double programmingScore) {
        this.programmingScore = programmingScore;
    }

    public double getMathematicsScore() {
        return mathematicsScore;
    }

    public void setMathematicsScore(double mathematicsScore) {
        this.mathematicsScore = mathematicsScore;
    }

    public List<String> getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(List<String> technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    // Calculate average
    public double getAverage() {
        return (programmingScore + mathematicsScore) / 2;
    }

    // Performance category
    public String getPerformance() {

        double avg = getAverage();

        if (avg >= 85) {
            return "Excellent";
        } else if (avg >= 70) {
            return "Good";
        } else if (avg >= 50) {
            return "Satisfactory";
        } else {
            return "Needs Improvement";
        }
    }

    // Stronger subject
    public String getStrongerSubject() {

        if (programmingScore > mathematicsScore) {
            return "Programming";
        } else if (mathematicsScore > programmingScore) {
            return "Mathematics";
        } else {
            return "Both are equal";
        }
    }

    // Object methods
    @Override
    public String toString() {
        return "Student ID: " + studentId
                + ", Name: " + studentName
                + ", Average: " + getAverage()
                + ", Performance: " + getPerformance();
    }
}
