package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service;

import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Author;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Book;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.BookCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Book findBookById(Long id);
    List<Book> findBooksByAuthor(Long authorId);
    List<Book> findAll();
    Book create(String name, Author author, BookCategory bookCategory, Integer availableCopies);
    Book delete(Long bookId);

}
