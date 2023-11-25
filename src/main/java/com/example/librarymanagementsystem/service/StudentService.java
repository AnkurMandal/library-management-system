package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student) {
        LibraryCard libraryCard =new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);//set lib card for student
        Student savedStudent=studentRepository.save(student);//saved both student and lib card
        return "Student saved successfully";
    }

    public Student getStudent(int regNo) {
        Optional<Student>studentOptional=studentRepository.findById(regNo);
        if(studentOptional.isPresent())return studentOptional.get();
        return null;
    }
}
