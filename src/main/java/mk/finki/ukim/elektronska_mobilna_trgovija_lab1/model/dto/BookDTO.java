package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.dto;

import lombok.Data;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.BookCategory;

@Data
public class BookDTO {
    private String name;
    private BookCategory category;
    private Long authorId;
    private Integer availableCopies;
}
