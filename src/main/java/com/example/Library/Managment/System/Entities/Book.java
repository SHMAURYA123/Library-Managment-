package com.example.Library.Managment.System.Entities;

import com.example.Library.Managment.System.Enums.Genre;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private int price;

    @Enumerated(value=EnumType.STRING)
    private Genre genre;

    private double rating;
    private boolean isAvailable;
    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();

}
