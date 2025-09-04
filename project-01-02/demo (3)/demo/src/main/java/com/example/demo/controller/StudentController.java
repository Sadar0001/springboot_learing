package com.example.demo.controller;


import com.example.demo.dto.StudentRequestDto;
import com.example.demo.dto.StudentResponseDto;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public StudentResponseDto createStudent(@RequestBody StudentRequestDto dto) {
        return studentService.createStudent(dto);
    }

    @GetMapping
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PutMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable Long id, @RequestBody StudentRequestDto dto) {
        return studentService.updateStudent(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

}
