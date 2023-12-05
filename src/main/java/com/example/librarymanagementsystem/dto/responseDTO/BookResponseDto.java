package com.example.librarymanagementsystem.dto.responseDTO;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.model.Author;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResponseDto {
    String title;

    int noOfPages;


    Genre genre;

    double cost;

    String authorName;

}
