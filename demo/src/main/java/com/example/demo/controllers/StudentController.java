package com.example.demo.controllers;

import com.example.demo.dto.CreatedDto;
import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedDto addStudent(@Valid @RequestBody Student student) {
        Long id = studentService.addStudent(student);
        CreatedDto createdDto = new CreatedDto(Student.class.getSimpleName(), id.toString());

        return createdDto;
    }

    @DeleteMapping(path = "{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(
            @PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
}
