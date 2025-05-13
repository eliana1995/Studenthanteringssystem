package com.example.studentapp.controller;

import com.example.studentapp.model.Student;
import com.example.studentapp.service.StudentService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {

    private final StudentService mockService = mock(StudentService.class);
    private final StudentController controller = new StudentController(mockService);

    // Test för GET /students
    @Test
    void testGetAllStudents() {
        // Arrange
        Student student = new Student(1L, "Test Student", null);
        when(mockService.getAllStudents()).thenReturn(List.of(student));

        // Act
        List<Student> result = controller.getAllStudents();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Student", result.get(0).getName());
    }

    // Test för POST /students
    @Test
    void testCreateStudent() {
        // Arrange
        Student newStudent = new Student("New Student");
        Student createdStudent = new Student(1L, "New Student", null);  // Mockad skapad student
        when(mockService.createStudent(newStudent)).thenReturn(createdStudent);

        // Act
        Student result = controller.createStudent(newStudent);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Student", result.getName());
        verify(mockService, times(1)).createStudent(newStudent);  // Kontrollera att metoden skapades en gång
    }

    // Test för DELETE /students/{id}
    @Test
    void testDeleteStudent() {
        // Arrange
        Long studentId = 1L;
        doNothing().when(mockService).deleteStudent(studentId);  // Mocka att metoden gör ingenting när den kallas

        // Act
        controller.deleteStudent(studentId);

        // Assert
        verify(mockService, times(1)).deleteStudent(studentId);  // Kontrollera att deleteStudent anropades en gång
    }
}

