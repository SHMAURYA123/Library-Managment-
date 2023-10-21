package com.example.Library.Managment.System.Entities;

import com.example.Library.Managment.System.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="library_card")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {
    @Id
    private Integer cardNo;
    @Enumerated(value= EnumType.STRING)
    private CardStatus cardStatus;

    @OneToOne
    @JoinColumn
    private Student student;
}
