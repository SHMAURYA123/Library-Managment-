package com.example.Library.Managment.System.Repository;

import com.example.Library.Managment.System.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
