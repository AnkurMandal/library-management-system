package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(int regNo) {
        Optional<Student>studentOptional=studentRepository.findById(regNo);
        if(studentOptional.isPresent())return studentOptional.get();
        return null;
    }
}
