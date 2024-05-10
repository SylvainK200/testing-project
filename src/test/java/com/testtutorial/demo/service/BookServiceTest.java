package com.testtutorial.demo.service;

import com.testtutorial.demo.entity.Book;
import com.testtutorial.demo.repository.BookRepository;
import exceptionhandler.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;


    @InjectMocks
    private BookService bookService;

    static Book book1;
    static Book book2;

    @BeforeAll
    static void setUp() {
        book1 = Book.builder()
                .author("KOUEMO")
                .name("Begining to end")
                .numberOfPages(200)
                .build();

        book2 = Book.builder()
                .author("Sylvain")
                .name("From Begining to end")
                .numberOfPages(250)
                .build();
    }

    @Test
    void saveTest() {
        when(bookRepository.save(book1)).thenReturn(Book.builder()
                .id(1)
                .author("KOUEMO")
                .name("Begining to end")
                .numberOfPages(200)
                .build());

        Book savedBook = bookService.create(book1);
        assertEquals(savedBook.getId(),1);
    }

    @Test
    void findByIdTest(){
        when(bookRepository.save(book1)).thenReturn(Book.builder()
                .id(1)
                .author("KOUEMO")
                .name("Begining to end")
                .numberOfPages(200)
                .build());

        Book savedBook = bookService.create(book1);

        Exception exception =  assertThrows(ObjectNotFoundException.class, ()->bookService.findById(2) );
        String expectedMessage = "Object not found";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}
