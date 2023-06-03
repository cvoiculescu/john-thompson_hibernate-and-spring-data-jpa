package org.voiculescu.sdjpaspringdataqueries.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.voiculescu.sdjpaspringdataqueries.entity.Book;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ComponentScan(basePackages = "org.voiculescu.sdjpaspringdataqueries.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testEmptyResultException(){
        assertThrows(EmptyResultDataAccessException.class,()->{
            Book book = bookRepository.readByTitle("foobar4");
        });
    }

    @Test
    void testNullParam(){
        assertThrows(IllegalArgumentException.class,()->{
            Book book = bookRepository.getByTitle(null);
        });
    }

    @Test
    void testNoException(){
        assertNull(bookRepository.getByTitle("foo"));
    }

    @Test
    void testBookStream(){
        AtomicInteger count = new AtomicInteger();
        bookRepository.findAllByTitleNotNull()
                .forEach(book -> {
                    count.incrementAndGet();
                });
        assertThat(count.get()).isGreaterThan(5);


    }

}
