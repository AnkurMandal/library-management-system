package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponseDto;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;
    public String addBook(Book book){

        //check if author exists or not
        Optional<Author> authorOptional=authorRepository.findById(book.getAuthor().getId());
        if(authorOptional.isEmpty()){
            throw new AuthorNotFoundException("Invalid author id!!");
        }
        Author author =authorOptional.get();
        book.setAuthor(author);

        author.getBooks().add(book);
        authorRepository.save(author);//save book author and book

        return "Book added successfully";
    }

    public List<BookResponseDto> getBookByGenreAndGreaterThanCost(String genre, double cost) {
        List<Book>books=bookRepository.getBookByGenreAndGreaterThanCost(genre,cost);

        //prepare the response , convert model to dto
        List<BookResponseDto> bookResponseDtos=new ArrayList<>();
        for(Book book:books){
            BookResponseDto bookResponseDto=new BookResponseDto();

            bookResponseDto.setTitle(book.getTitle());
            bookResponseDto.setNoOfPages(book.getNoOfPages());
            bookResponseDto.setGenre(book.getGenre());
            bookResponseDto.setCost(book.getCost());
            bookResponseDto.setAuthorName(book.getAuthor().getName());

            bookResponseDtos.add(bookResponseDto);
        }
        return bookResponseDtos;

    }

    public List<BookResponseDto> getBookByGenreAndGreaterThanCostHql(Genre genre, double cost) {
        List<Book>books=bookRepository.getBookByGenreAndGreaterThanCostHql(genre,cost);

        //prepare the response , convert model to dto
        List<BookResponseDto> bookResponseDtos=new ArrayList<>();
        for(Book book:books){
            BookResponseDto bookResponseDto=new BookResponseDto();

            bookResponseDto.setTitle(book.getTitle());
            bookResponseDto.setNoOfPages(book.getNoOfPages());
            bookResponseDto.setGenre(book.getGenre());
            bookResponseDto.setCost(book.getCost());
            bookResponseDto.setAuthorName(book.getAuthor().getName());

            bookResponseDtos.add(bookResponseDto);
        }
        return bookResponseDtos;

    }
}
