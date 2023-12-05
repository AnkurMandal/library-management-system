package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "select * from book where genre=:genre and cost>:cost",nativeQuery = true)
    List<Book>getBookByGenreAndGreaterThanCost(String genre, double cost);

    //Hibernate Query Language
    @Query(value = "select b from Book b where b.genre=:genre and cost>:cost",nativeQuery = false)
    List<Book> getBookByGenreAndGreaterThanCostHql(Genre genre, double cost);
}
