package com.example.Library.Managment.System.Controllers;

import com.example.Library.Managment.System.Entities.Book;
import com.example.Library.Managment.System.Enums.Genre;
import com.example.Library.Managment.System.Service.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addbook")
    public ResponseEntity<String> addBook(@RequestBody Book book, @RequestParam("authorId") Integer authorId){
        try{
            String result=bookService.addBook(book,authorId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBookByGenre")
    public List<String> getBooksByGenre(@RequestParam("book") Genre genre){
         return bookService.getBooksByGenre(genre);
    }
}
