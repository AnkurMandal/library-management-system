package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponseDto;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody Book book){
        try {
            String response=bookService.addBook(book);
            return response;
        }catch (AuthorNotFoundException e){
            return e.getMessage();
        }
    }
    //delete a book

    //give me names of all the books of a particular genre

    //give me names of all the books of a particular genre and cost greater than 500rs

    @GetMapping("/get-books-by-genre-and-cost")
    public List<BookResponseDto> getBookByGenreAndGreaterThanCost(@RequestParam ("genre") String genre,@RequestParam ("cost") double cost){
        return bookService.getBookByGenreAndGreaterThanCost(genre,cost);
    }


    //Hibernate Query Language
    @GetMapping("/get-books-by-genre-and-cost-hql")
    public List<BookResponseDto> getBookByGenreAndGreaterThanCostHql(@RequestParam ("genre") Genre genre,@RequestParam ("cost") double cost){
        return bookService.getBookByGenreAndGreaterThanCostHql(genre,cost);
    }

    //gave me all the books having no. of pages btw 'a' and 'b'

    //give me the name of all the author who write the particular genre

}
