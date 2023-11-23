package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="student_info")
public class Student {

      @Id
      int regNo;

      @Column(name="student_name")
      String name;

      int age;

      String email;

      @Enumerated(EnumType.STRING)
      Gender gender;

      @OneToOne(mappedBy = "student")
      LibraryCard libraryCard;
}
