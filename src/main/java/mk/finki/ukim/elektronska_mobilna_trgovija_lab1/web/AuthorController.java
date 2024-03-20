package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.web;

import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Author;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.dto.AuthorDTO;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository.AuthorRepository;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository.BookRepository;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository.CountryRepository;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service.AuthorService;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorController(BookService bookService, AuthorService authorService, BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @GetMapping()
    public List<Author> getAllAuthors(){
        return this.authorService.findAll();
    }

    @GetMapping("/{authorId}")
    public Author findAuthorById(@PathVariable Long authorId){
        Author author = this.authorService.findAuthorById(authorId);
        return author;
    }

    @PostMapping("/create")
    public Author createAuthor(@RequestBody AuthorDTO authorDTO){
        return this.authorRepository.save(new Author(authorDTO.getName(), authorDTO.getSurname(), this.countryRepository.findById(authorDTO.getCountryId()).orElse(null)));
    }
    @DeleteMapping("/{authorId}/delete")
    public Author deleteAuthor(@PathVariable Long authorId){
        Author author = this.authorService.findAuthorById(authorId);
        this.authorRepository.delete(author);
        return author;
    }

}
