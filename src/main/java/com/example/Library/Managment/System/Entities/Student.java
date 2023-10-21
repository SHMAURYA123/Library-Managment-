package com.example.Library.Managment.System.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // this tell how my schema reflected in db
@Table(name="student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    private Integer studentId;
    private String name;
    private int age;
    @Column(name="contact-no",unique = true,nullable = false)
    private String mobNo;
    private String emailId;
    private String bloodGroup;

}
