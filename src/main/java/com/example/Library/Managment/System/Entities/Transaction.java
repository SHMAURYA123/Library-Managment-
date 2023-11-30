package com.example.Library.Managment.System.Entities;

import com.example.Library.Managment.System.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Transaction {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

 @Enumerated(value = EnumType.STRING)
 private TransactionStatus transactionStatus;

 private Integer fine;

 private Date returnDate;

 @CreationTimestamp
 private Date createdOn;  // handled by spring internally

    @UpdateTimestamp
    private Date lastModifiedOn; //handled by spring internally

    //connect fk
    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private LibraryCard card;
}
