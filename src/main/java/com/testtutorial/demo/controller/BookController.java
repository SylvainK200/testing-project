package com.testtutorial.demo.controller;

import com.testtutorial.demo.entity.Book;
import com.testtutorial.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController extends GeneralController{

    private final BookService bookService;

    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> findAll(){
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/books/{id}")
    public  ResponseEntity<Book> findById(@PathVariable Integer id){
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.FOUND);
    }

    @DeleteMapping(value="/books/{id}")
    public void deleteById(@PathVariable Integer id ){
        bookService.delete(id);
    }


    @PutMapping(value="/books")
    public ResponseEntity<Book> update(@RequestBody Book book)  {
        return new ResponseEntity<>(bookService.update(book),HttpStatus.OK);
    }


    @PostMapping(value="/books")
    public ResponseEntity<Book> create(@RequestBody Book book){
        return new ResponseEntity<>(bookService.create(book), HttpStatus.CREATED);
    }



}
