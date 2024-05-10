package com.testtutorial.demo.service;

import com.testtutorial.demo.entity.Book;
import com.testtutorial.demo.repository.BookRepository;
import exceptionhandler.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;


    public Book create(Book book){
        return bookRepository.save(book);
    }

    public Book update(Book book){
        if(bookRepository.findById(book.getId()).isPresent()){
            return bookRepository.save(book);
        }
        throw new ObjectNotFoundException("Object not found");
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public void delete(Integer id){
        if(bookRepository.findById(id).isPresent()){
           bookRepository.deleteById(id);
        }
        throw new ObjectNotFoundException("Object not found");
    }

    public Book findById(Integer id){
        return bookRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException("Object not found"));
    }
}
