package com.example.librarymanagementsystem;

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
