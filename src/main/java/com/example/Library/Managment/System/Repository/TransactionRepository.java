package com.example.Library.Managment.System.Repository;

import com.example.Library.Managment.System.Entities.Book;
import com.example.Library.Managment.System.Entities.LibraryCard;
import com.example.Library.Managment.System.Entities.Transaction;
import com.example.Library.Managment.System.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
  Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);
}
