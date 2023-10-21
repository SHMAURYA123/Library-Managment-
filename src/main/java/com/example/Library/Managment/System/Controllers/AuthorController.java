package com.example.Library.Managment.System.Controllers;

import com.example.Library.Managment.System.Entities.Author;
import com.example.Library.Managment.System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){

        String response=authorService.addAuthor(author);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
@GetMapping("/findAllAuthorNames")
    public List<String> getALlAuthorNames(){
        return authorService.getALlAuthorNames();
    }

    public ResponseEntity getAuthor(@PathVariable("id") Integer id){
     try{
         Author author=authorService.getAuthor(id);
         return new ResponseEntity<>(author,HttpStatus.OK);
     }
     catch (Exception e){
         return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
     }
    }
    public  ResponseEntity getBookNameList(@PathVariable("authorid") Integer authorId){
        List<String>bookNames=authorService.getBookNameList(authorId);
        return new ResponseEntity<>(bookNames,HttpStatus.OK);
    }

}
