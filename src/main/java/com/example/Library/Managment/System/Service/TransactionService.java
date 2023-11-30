package com.example.Library.Managment.System.Service;

import com.example.Library.Managment.System.Entities.Book;
import com.example.Library.Managment.System.Entities.LibraryCard;
import com.example.Library.Managment.System.Entities.Transaction;
import com.example.Library.Managment.System.Enums.CardStatus;
import com.example.Library.Managment.System.Enums.TransactionStatus;
import com.example.Library.Managment.System.Exceptions.*;
import com.example.Library.Managment.System.Repository.BookRepository;
import com.example.Library.Managment.System.Repository.CardRepository;
import com.example.Library.Managment.System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {


    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TransactionRepository transactionRepository;

     private static final Integer MAX_LIMIT_OF_BOOK=3;
     private static final Integer FINE_PER_DAY=5;
    public String issueBook(Integer bookId, Integer cardId) throws Exception {
        Transaction transaction=new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        //validation
        Optional<Book> bookOptional=bookRepository.findById(bookId);
        if(!bookOptional.isPresent()){
            throw new BookNotFound("BookId entered is Invalid");
        }
        Book book=bookOptional.get();
        if(!book.isAvailable()){
            throw new BookNotAvailable("book is not available");
        }

        Optional<LibraryCard>libraryCardOptional=cardRepository.findById(cardId);
        if (!libraryCardOptional.isPresent()){
            throw new CardNotFound("CardId entered is Invalid");
        }
        LibraryCard card=libraryCardOptional.get();

        if(!card.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new InvalidCardStatus("card status is not ACTIVE");
        }

        if(card.getNoOfBooksIssue().equals(MAX_LIMIT_OF_BOOK)){
           throw new MaxBookAlreadyIssued(MAX_LIMIT_OF_BOOK+"is maximum book that can be issued");
        }


        transaction.setTransactionStatus(TransactionStatus.ISSUED);
        card.setNoOfBooksIssue(card.getNoOfBooksIssue()+1);

        //book no longer be available
        book.setAvailable(false);
        //setting FK
        transaction.setBook(book);
        transaction.setCard(card);

        // setting bidirectionalMapping
        book.getTransactionList().add(transaction);
        card.getTransactionList().add(transaction);

        //Instead of saving the parent: save in child
        transactionRepository.save(transaction);
        return "The Book with bookId"+bookId+"has been issued to cardId"+cardId;
    }

    public String returnBook(Integer bookId, Integer cardId) {
        Book book=bookRepository.findById(bookId).get();
        LibraryCard card=cardRepository.findById(cardId).get();


        Transaction transaction=transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);
        Date issueDate=transaction.getCreatedOn();
        long millisecond=Math.abs(System.currentTimeMillis()-issueDate.getTime());
        long days= TimeUnit.DAYS.convert(millisecond,TimeUnit.MILLISECONDS);
        int fineAmount=0;
        if(days>15){
            fineAmount=Math.toIntExact((days-15)*FINE_PER_DAY);
        }
        Transaction newTransaction=new Transaction();
        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setFine(fineAmount);
        newTransaction.setReturnDate(new Date());

        //Setting FK's
        newTransaction.setBook(book);
        newTransaction.setCard(card);

        book.setAvailable(true);
        card.setNoOfBooksIssue(card.getNoOfBooksIssue()-1);

        book.getTransactionList().add(newTransaction);
        card.getTransactionList().add(newTransaction);

        transactionRepository.save(newTransaction);
        return "Book has been returned";
    }
}
