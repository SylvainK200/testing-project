package com.testtutorial.demo.controller;

import com.testtutorial.demo.entity.Book;
import com.testtutorial.demo.service.BookService;
import exceptionhandler.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerUnitTest {

    @InjectMocks
    BookController bookController;

    @Mock
    BookService bookService;

    Book book;

    @BeforeEach
    void setUp(){
        book = Book.builder()
                .numberOfPages(200)
                .name("Testing App")
                .author("KOUEMO")
                .build();
    }

    @Test
    public void testCreateBook(){
        MockHttpServletRequest request =  new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        book.setId(1);
        when(bookService.create(book)).thenReturn(book);

        ResponseEntity<Book> responseEntity = bookController.create(book);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
        assertEquals(Objects.requireNonNull(responseEntity.getBody()).getId(), 1);
    }

    @ParameterizedTest
    @ValueSource(ints={1})
    public void testFindById(int id){
        if(id==1){
            book.setId(1);
            when(bookService.findById(id)).thenReturn(book);
            MockHttpServletRequest request =  new MockHttpServletRequest();
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

            ResponseEntity<Book> responseEntity = bookController.findById(id);
            assertEquals(responseEntity.getStatusCode(), HttpStatus.FOUND);
            assertEquals(Objects.requireNonNull(responseEntity.getBody()).getId(), 1);
        }
    }




}
