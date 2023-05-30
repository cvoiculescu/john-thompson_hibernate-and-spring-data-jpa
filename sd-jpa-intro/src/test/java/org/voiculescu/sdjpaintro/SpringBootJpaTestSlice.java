package org.voiculescu.sdjpaintro;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.sdjpaintro.domain.Book;
import org.voiculescu.sdjpaintro.repository.BookRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"org.voiculescu.sdjpaintro.bootstrap"})
class SpringBootJpaTestSlice {

    @Autowired
    BookRepository bookRepository;

    @Commit
    @Order(1)
    @Test
    public void testJpaTestSlice(){
        long countBefore = bookRepository.count();
        bookRepository.save(new Book("MyBook","isbn","MyPublisher"));
        long countAfter = bookRepository.count();
        assertThat(countAfter).isGreaterThan(countBefore);
    }

    @Order(2)
    @Test
    public void testJpaTestSliceTransactional(){
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);
        bookRepository.save(new Book("MyBook1","isbn1","MyPublisher1"));
        long countAfter = bookRepository.count();
        assertThat(countAfter).isGreaterThan(countBefore);
    }

}
