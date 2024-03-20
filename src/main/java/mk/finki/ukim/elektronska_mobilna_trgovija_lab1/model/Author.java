package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;

    @ManyToOne
    Country originCountry;
    public Author(){}

    public Author(String name, String surname, Country originCountry) {
        this.name = name;
        this.surname = surname;
        this.originCountry = originCountry;
    }
}
