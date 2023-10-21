package com.example.Library.Managment.System.Repository;

import com.example.Library.Managment.System.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

}
