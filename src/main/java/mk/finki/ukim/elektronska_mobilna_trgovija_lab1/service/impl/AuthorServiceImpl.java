package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service.impl;

import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Author;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository.AuthorRepository;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }
}
