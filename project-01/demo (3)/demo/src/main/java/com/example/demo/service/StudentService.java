package com.example.demo.service;

import com.example.demo.dto.StudentRequestDto;
import com.example.demo.dto.StudentResponseDto;
import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentResponseDto createStudent(StudentRequestDto dto) {
        StudentEntity student = new StudentEntity();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword()); // ⚠️ Should hash in real-world apps
        StudentEntity saved = studentRepository.save(student);
        return mapToResponseDto(saved);
    }

    public StudentResponseDto getStudent(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
        return mapToResponseDto(student);
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto updateStudent(Long id, StudentRequestDto dto) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword()); // ⚠️ Should hash
        StudentEntity updated = studentRepository.save(student);
        return mapToResponseDto(updated);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // ✅ Mapping helper
    private StudentResponseDto mapToResponseDto(StudentEntity entity) {
        return new StudentResponseDto(entity.getId(), entity.getName(), entity.getEmail());
    }
}
