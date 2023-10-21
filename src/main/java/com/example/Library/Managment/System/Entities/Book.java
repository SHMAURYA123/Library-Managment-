package com.example.Library.Managment.System.Entities;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table // Incase you cannot write any name . classname is taken as table name
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer bookId;
    private String bookName;
    private int noOfPages;
    private int Price;

    private double rating;

    @ManyToOne
    @JoinColumn
    private Author author;
}
