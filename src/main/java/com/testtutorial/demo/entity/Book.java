package com.testtutorial.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String author;

    private Integer numberOfPages;
}
