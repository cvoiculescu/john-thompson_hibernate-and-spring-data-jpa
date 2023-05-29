package org.voiculescu.sdjpaintro.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.voiculescu.sdjpaintro.domain.Book;
import org.voiculescu.sdjpaintro.repository.BookRepository;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book bookDDD = new Book().setTitle("Domain Driven Design").setPublisher("Random House");
        log.info("{} -> {}",bookDDD.getId(),bookDDD.getTitle());
        Book savedDDD = bookRepository.save(bookDDD);
        Book bookSIA = new Book().setTitle("Spring in Action").setPublisher("O'Reilly");
        log.info("{} -> {}",bookSIA.getId(),bookSIA.getTitle());
        Book savedSIA = bookRepository.save(bookSIA);
        bookRepository.findAll().forEach(book->log.info("{} -> {}",book.getId(),book.getTitle()));
    }
}
