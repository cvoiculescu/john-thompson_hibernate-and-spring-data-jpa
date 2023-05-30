package org.voiculescu.sdjpaintro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.voiculescu.sdjpaintro.domain.Book;
import org.voiculescu.sdjpaintro.repository.BookRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@ComponentScan(basePackages = {"org.voiculescu.sdjpaintro.bootstrap"})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testMySQLIntegration(){
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
        bookRepository.save(new Book("MyBook1","isbn1","MyPublisher1","1"));
        long countAfter = bookRepository.count();
        assertThat(countAfter).isGreaterThan(countBefore);
    }

}
