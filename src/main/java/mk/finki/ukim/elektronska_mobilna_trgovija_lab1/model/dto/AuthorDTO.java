package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.dto;

import lombok.Data;

@Data
public class AuthorDTO {
    private String name;
    private String surname;
    //TODO : AuthorDTO, author controller crud operations, same for country controller, finish book controller
    private Long countryId;
}
