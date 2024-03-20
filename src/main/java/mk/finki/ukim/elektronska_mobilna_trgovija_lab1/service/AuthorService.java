package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service;

import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    Author findAuthorById(Long id);
    List<Author> findAll();
}
