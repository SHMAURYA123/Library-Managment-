package com.example.Library.Managment.System.Controllers;

import com.example.Library.Managment.System.Entities.LibraryCard;
import com.example.Library.Managment.System.Entities.Student;
import com.example.Library.Managment.System.Enums.CardStatus;
import com.example.Library.Managment.System.Repository.StudentRepository;
import com.example.Library.Managment.System.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("card")
public class CardController {
    @Autowired
    private CardService cardService;


    @PostMapping("/generatePlainCard")
    public ResponseEntity generatePlainCard(){
        LibraryCard newCard=cardService.generatePlainCard();
        String response="The new card is generate and having a card no"+ newCard.getCardNo();
       return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/associatedWithStudent")
    public ResponseEntity associatedWithStudent(@RequestParam("studentId") Integer studentId,@RequestParam("cardNo") Integer cardNo){
        String res=cardService.associatedWithStudent(studentId,cardNo);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
