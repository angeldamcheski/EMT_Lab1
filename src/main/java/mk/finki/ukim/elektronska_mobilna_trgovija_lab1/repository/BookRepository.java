package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.repository;

import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Author;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByAuthor(Author author);
}
