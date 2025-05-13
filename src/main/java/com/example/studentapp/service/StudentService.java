package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepo;

    // Konstruktor
    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    // Hämta alla studenter
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // Hämta student med ID
    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    // Skapa en ny student
    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    // Ta bort en student
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }
}
