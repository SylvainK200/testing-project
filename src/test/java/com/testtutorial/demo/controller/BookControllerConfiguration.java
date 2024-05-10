package com.testtutorial.demo.controller;

import com.testtutorial.demo.repository.BookRepository;
import com.testtutorial.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class BookControllerConfiguration{
    @Autowired
    private BookRepository bookRepository;
    @Bean
    public BookService bookService(){
        return new BookService(this.bookRepository);
    }
}
