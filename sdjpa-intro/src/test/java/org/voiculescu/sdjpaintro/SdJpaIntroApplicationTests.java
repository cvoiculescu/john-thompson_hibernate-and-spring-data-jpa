package org.voiculescu.sdjpaintro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.voiculescu.sdjpaintro.repository.BookRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SdJpaIntroApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testBookRepository(){
        long count = bookRepository.count();
        assertThat(count).isGreaterThan(0);
    }

    @Test
    void contextLoads() {
    }

}
