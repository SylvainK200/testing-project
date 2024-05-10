package com.testtutorial.demo.controller;

import com.testtutorial.demo.entity.Book;
import com.testtutorial.demo.repository.BookRepository;
import com.testtutorial.demo.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@Import(BookControllerConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;


    @Autowired
    private BookService bookService;


    List<Book> books;
    @BeforeEach
    void setUp(){
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

        books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        when((bookRepository.findAll())).thenReturn(books);
    }

    @Test
    public void getAllBooks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());

    }



}
