package org.voiculescu.sdjpaintro.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.voiculescu.sdjpaintro.domain.AuthorUuid;
import org.voiculescu.sdjpaintro.domain.Book;
import org.voiculescu.sdjpaintro.repository.AuthorUuidRepository;
import org.voiculescu.sdjpaintro.repository.BookRepository;

@Profile({"default", "local"})
@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorUuidRepository authorUuidRepository;

    public DataInitializer(BookRepository bookRepository, AuthorUuidRepository authorUuidRepository) {
        this.bookRepository = bookRepository;
        this.authorUuidRepository = authorUuidRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();
        Book bookDDD = new Book().setTitle("Domain Driven Design").setPublisher("Random House");

        bookRepository.save(bookDDD);
        Book bookSIA = new Book().setTitle("Spring in Action").setPublisher("O'Reilly");

        bookRepository.save(bookSIA);

        AuthorUuid author = new AuthorUuid().setFirstName("Author").setLastName("LastName");
        AuthorUuid savedAuthor
                = authorUuidRepository.save(author);
        log.info("Author: {}",author.getId());

    }
}
