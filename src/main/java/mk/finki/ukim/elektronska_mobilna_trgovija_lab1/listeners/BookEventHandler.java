package mk.finki.ukim.elektronska_mobilna_trgovija_lab1.listeners;

import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.model.events.BookCreatedEvent;
import mk.finki.ukim.elektronska_mobilna_trgovija_lab1.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventHandler {
    private final BookService bookService;

    public BookEventHandler(BookService bookService) {
        this.bookService = bookService;
    }
    @EventListener
    public void onBookCreated(BookCreatedEvent bookCreatedEvent){
        System.out.println("Knigata sega ima " + bookCreatedEvent.getAvailableCopies()+" kopii");
    }
}
