package com.example.Library.Managment.System.Service;

import com.example.Library.Managment.System.Entities.Author;
import com.example.Library.Managment.System.Entities.Book;
import com.example.Library.Managment.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        authorRepository.save(author);
        return  "Author has been added to DB";
    }

    public List<String> getALlAuthorNames() {
        List<Author> authors=authorRepository.findAll();
        List<String> authorNames=new ArrayList<>();

        for(Author author:authors){
            authorNames.add(author.getAuthorName());
        }
        return authorNames;
    }

    public Author getAuthor(Integer id) throws Exception {
        Optional<Author> optionalAuthor=authorRepository.findById(id);

        if(!optionalAuthor.isPresent()){
            throw new Exception("The id entered is invalid");
        }

        Author author=optionalAuthor.get();
        return author;
    }

    public List<String> getBookNameList(Integer authorId) {
     List<String> bookNameList=new ArrayList<>();
     Author author=authorRepository.findById(authorId).get();
     List<Book> bookList=author.getBookList();
     for(Book book:bookList){
         bookNameList.add(book.getBookName());
     }
     return bookNameList;
    }
}
