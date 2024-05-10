package com.testtutorial.demo.repository;


import com.testtutorial.demo.entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    void tearDown(){
        bookRepository.deleteAll();
    }




    @Test
    void findTestAll(){
        Book book1 = Book.builder()
                .author("KOUEMO")
                .name("Begining to end")
                .numberOfPages(200)
                .build();

        Book book2 = Book.builder()
                .author("Sylvain")
                .name("From Begining to end")
                .numberOfPages(250)
                .build();

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        bookRepository.saveAll(books);
        List<Book> allBooks = bookRepository.findAll();
        assertEquals(2, allBooks.size());
    }

    @Test
    void findTestById(){
        Book book = Book.builder()
                .author("KOUEMO")
                .name("Begining to end")
                .numberOfPages(200)
                .build();

        bookRepository.save(book);
        Optional<Book> existBook = bookRepository.findById(1);
        assertTrue(existBook.isPresent());
    }

    @Test
    void deleteTestById(){
        Book book = Book.builder()
                .author("KOUEMO")
                .name("Begining to end")
                .numberOfPages(200)
                .build();

        bookRepository.save(book);
        bookRepository.deleteById(1);

    }
}
