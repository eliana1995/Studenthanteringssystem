package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private final StudentRepository repo = mock(StudentRepository.class);
    private final StudentService service = new StudentService(repo);

    @Test
    void testGetStudentById() {
        Student student = new Student(1L, "Ali", null);
        when(repo.findById(1L)).thenReturn(Optional.of(student));

        Student result = service.getStudentById(1L);

        assertNotNull(result);
        assertEquals("Ali", result.getName());
    }
}
