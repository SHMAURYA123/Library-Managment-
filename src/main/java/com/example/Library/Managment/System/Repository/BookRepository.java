package com.example.Library.Managment.System.Repository;

import com.example.Library.Managment.System.Entities.Book;
import com.example.Library.Managment.System.Enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
   List<Book> findBooksByGenre(Genre genre);
   Book findBookByBookName(String bookName);
}
