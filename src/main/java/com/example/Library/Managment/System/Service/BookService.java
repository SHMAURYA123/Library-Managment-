package com.example.Library.Managment.System.Service;

import com.example.Library.Managment.System.Entities.Author;
import com.example.Library.Managment.System.Entities.Book;
import com.example.Library.Managment.System.Enums.Genre;
import com.example.Library.Managment.System.Exceptions.AuthorNotFoundException;
import com.example.Library.Managment.System.Repository.AuthorRepository;
import com.example.Library.Managment.System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public String addBook(Book book, Integer authorId) throws Exception{
        Optional<Author> optionalAuthor=authorRepository.findById(authorId);
        if(!optionalAuthor.isPresent()){
            throw new AuthorNotFoundException("AuthorId enter is invalid");
        }
        Author author=optionalAuthor.get();
        book.setAuthor(author);

        //author should have information of the book entity
        author.getBookList().add(book);

        // due to cascading effect if i saved parent detail it automatically saved in child class
        authorRepository.save(author);
        return "Added book in Db successfully";
    }

    public List<String> getBooksByGenre(Genre genre) {
        List<String> bookNames=new ArrayList<>();
        List<Book> bookList=bookRepository.findBooksByGenre(genre);
        for(Book book:bookList){
            bookNames.add(book.getBookName());
        }
        return bookNames;
    }
}
