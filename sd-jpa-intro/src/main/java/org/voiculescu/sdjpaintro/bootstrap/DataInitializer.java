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
        if (bookRepository.count() > 0) {
            return;
        }
        Book bookDDD = new Book().setTitle("Domain Driven Design").setPublisher("Random House");

        bookRepository.save(bookDDD);
        Book bookSIA = new Book().setTitle("Spring in Action").setPublisher("O'Reilly");

        bookRepository.save(bookSIA);
    }
}
