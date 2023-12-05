package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {

        //converting request dto to model
        Student student=new Student();
        student.setName(studentRequest.getName());
        student.setAge(studentRequest.getAge());
        student.setEmail(studentRequest.getEmail());
        student.setGender(studentRequest.getGender());

        //Assigning Library Card
        LibraryCard libraryCard =new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);//set lib card for student
        Student savedStudent=studentRepository.save(student);//saved both student and lib card

        //converting model to response dto
        StudentResponse studentResponse=new StudentResponse();

        studentResponse.setName(savedStudent.getName());
        studentResponse.setEmail(savedStudent.getEmail());
        studentResponse.setMessage("Student saved successfully");

        LibraryCardResponse libraryCardResponse=new LibraryCardResponse();
        libraryCardResponse.setCardNo(savedStudent.getLibraryCard().getCardNo());
        libraryCardResponse.setCardStatus(savedStudent.getLibraryCard().getCardStatus());
        libraryCardResponse.setIssuedDate(savedStudent.getLibraryCard().getIssuedDate());

        studentResponse.setLibraryCardResponse(libraryCardResponse);

        return studentResponse;
    }

    public StudentResponse getStudent(int regNo) {
        Optional<Student>studentOptional=studentRepository.findById(regNo);

        if(studentOptional.isPresent()){
            StudentResponse studentResponse=new StudentResponse();
            studentResponse.setName(studentOptional.get().getName());
            studentResponse.setEmail(studentOptional.get().getEmail());
            studentResponse.setMessage("Student added Successfully");

            LibraryCardResponse libraryCardResponse=new LibraryCardResponse();
            libraryCardResponse.setCardNo(studentOptional.get().getLibraryCard().getCardNo());
            libraryCardResponse.setCardStatus(studentOptional.get().getLibraryCard().getCardStatus());
            libraryCardResponse.setIssuedDate(studentOptional.get().getLibraryCard().getIssuedDate());

            studentResponse.setLibraryCardResponse(libraryCardResponse);
            return studentResponse;
        }
        return null;
    }

    public List<String> getAllMales() {
        List<String> names=new ArrayList<>();
//        List <Student> students=studentRepository.findByGender(Gender.MALE);
        List<Student> students=studentRepository.findAll();
        for(Student s:students){
            if(s.getGender().equals(Gender.MALE)){
                names.add(s.getName());
            }

        }
        return names;
    }
}
