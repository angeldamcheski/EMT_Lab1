package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.events;


import lombok.Getter;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.Book;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.dto.BookDTO;
import org.springframework.context.ApplicationEvent;

@Getter
public class BookCreatedEvent extends ApplicationEvent {
    private Integer availableCopies;
    public BookCreatedEvent(Book book){
        super(book);
    }
    public BookCreatedEvent(Book sourceBook, Integer availableCopies){
        super(sourceBook);
        this.availableCopies = availableCopies;
    }

}
