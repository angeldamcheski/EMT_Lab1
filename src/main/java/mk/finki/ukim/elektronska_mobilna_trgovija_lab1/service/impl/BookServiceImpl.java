package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service.impl;

import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Author;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Book;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.BookCategory;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.events.BookCreatedEvent;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository.AuthorRepository;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository.BookRepository;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Book findBookById(Long id) {
        return this.bookRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Book> findBooksByAuthor(Long authorId)
    {
        Author author = this.authorRepository.findAuthorById(authorId);
        return this.bookRepository.findBookByAuthor(author);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book create(String name, Author author, BookCategory category, Integer availableCopies) {
        Book b = new Book(name, author, category, availableCopies);
        this.bookRepository.save(b);
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(b, b.getAvailableCopies()));
        return b;
    }

    @Override
    public Book delete(Long bookId) {
        Book b = this.bookRepository.findById(bookId).orElse(null);
        this.bookRepository.delete(b);
        return b;
    }
}
