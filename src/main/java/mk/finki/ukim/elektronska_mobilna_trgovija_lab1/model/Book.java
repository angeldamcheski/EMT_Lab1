package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Enumerated
    BookCategory category;
    @ManyToOne
    Author author;
    Integer availableCopies;
    public Book(){}
    public Book(String name, Author author, BookCategory bookCategory, Integer availableCopies){
        this.name = name;
        this.category = bookCategory;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
