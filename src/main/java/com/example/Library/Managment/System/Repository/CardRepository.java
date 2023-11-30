package com.example.Library.Managment.System.Repository;

import com.example.Library.Managment.System.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {

}
