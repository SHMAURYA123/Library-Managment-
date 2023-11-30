package com.example.Library.Managment.System.Service;

import com.example.Library.Managment.System.Entities.LibraryCard;
import com.example.Library.Managment.System.Entities.Student;
import com.example.Library.Managment.System.Enums.CardStatus;
import com.example.Library.Managment.System.Repository.CardRepository;
import com.example.Library.Managment.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private StudentRepository studentRepository;
    public LibraryCard generatePlainCard() {

        LibraryCard card=new LibraryCard();
        card.setCardStatus(CardStatus.NEW);
        card=cardRepository.save(card);
        return card;
    }

    public String associatedWithStudent(Integer studentId, Integer cardNo) {
        Optional<Student> studentOptional=studentRepository.findById(studentId);
        Student student=studentOptional.get();

        Optional<LibraryCard> libraryCardOptional=cardRepository.findById(cardNo);
        LibraryCard card=libraryCardOptional.get();

        card.setCardStatus(CardStatus.ACTIVE);
        card.setName(student.getName());
        card.setStudent(student);

        student.setLibraryCard(card);
        studentRepository.save(student);
        return "Card with cardNo"+cardNo+" has been associated  with studentId"+studentId;
    }
}
