package com.example.studentapp.controller;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        studentRepository.deleteAll();
    }

    // POST-test för att skapa en student
    @Test
    public void testCreateStudent() {

        Student newStudent = new Student("Emma");


        ResponseEntity<Student> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/students", newStudent, Student.class);


        assertThat(response.getStatusCodeValue()).isEqualTo(200);


        Student createdStudent = response.getBody();
        assertThat(createdStudent).isNotNull();
        assertThat(createdStudent.getName()).isEqualTo("Emma");

        // Kontrollera att studenten finns i databasen
        Student savedStudent = studentRepository.findById(createdStudent.getId()).orElse(null);
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getName()).isEqualTo("Emma");
    }

    // DELETE-test för att ta bort en student
    @Test
    public void testDeleteStudent() {

        Student student = studentRepository.save(new Student("eliana"));


        restTemplate.delete("http://localhost:" + port + "/students/" + student.getId());


        assertThat(studentRepository.findById(student.getId()).isPresent()).isFalse();
    }
}
