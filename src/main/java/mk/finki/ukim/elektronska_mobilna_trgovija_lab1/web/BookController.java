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
import org.springframework.context.ApplicationEventPublisher;
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
    private final ApplicationEventPublisher applicationEventPublisher;


    public BookController(BookService bookService, AuthorService authorService, BookRepository bookRepository, CountryRepository countryRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
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
//        Book book = new Book(book.getName(), authorService.findAuthorById(book.getAuthorId()), book.getCategory(), book.getAvailableCopies()));
        Book bookC = new Book(book.getName(), authorService.findAuthorById(book.getAuthorId()), book.getCategory(), book.getAvailableCopies());
//        this.bookRepository.save(bookC);
        this.bookService.create(book.getName(), this.authorService.findAuthorById(book.getAuthorId()),book.getCategory(),book.getAvailableCopies());
        applicationEventPublisher.publishEvent(book);
        return bookC;
    }
    @PostMapping("{id}/take")
    public String takeBook(@PathVariable Long id){
        Book b = this.bookService.findBookById(id);
        if(b.getAvailableCopies() <= 0){
            return "Book " + b.getName() + " is out of stock.";
        }
        b.setAvailableCopies(b.getAvailableCopies() - 1);
        this.bookRepository.save(b);
        return "You took " + b.getName();
    }

    @DeleteMapping("/{id}/delete")
    public Book deleteBook(@PathVariable Long id){
        return this.bookService.delete(id);
    }

    @PutMapping("/{id}/edit")
    public Book editBook(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        Book b = this.bookService.findBookById(id);
        b.setName(bookDTO.getName());
        b.setCategory(bookDTO.getCategory());
        b.setAuthor(this.authorService.findAuthorById(bookDTO.getAuthorId()));
        b.setAvailableCopies(bookDTO.getAvailableCopies());
        this.bookRepository.save(b);
        return b;
    }

}
