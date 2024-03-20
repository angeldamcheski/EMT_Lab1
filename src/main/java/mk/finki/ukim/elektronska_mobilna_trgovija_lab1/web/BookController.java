package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.web;

import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Author;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Book;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.BookCategory;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Country;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.dto.BookDTO;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.dto.CountryDTO;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository.BookRepository;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository.CountryRepository;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service.AuthorService;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;


    public BookController(BookService bookService, AuthorService authorService, BookRepository bookRepository, CountryRepository countryRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

    @GetMapping()
    public List<Book> getBooks(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id){
        return this.bookService.findBookById(id);
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody BookDTO book){
        return this.bookRepository.save(new Book(book.getName(), authorService.findAuthorById(book.getAuthorId()), book.getCategory(), book.getAvailableCopies()));
    }

    @DeleteMapping("/{id}/delete")
    public Book deleteBook(@PathVariable Long id){
        return this.bookService.delete(id);
    }


}
