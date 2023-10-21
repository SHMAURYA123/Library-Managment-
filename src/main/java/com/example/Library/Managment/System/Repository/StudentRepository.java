package com.example.Library.Managment.System.Repository;

import com.example.Library.Managment.System.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

    }

