package org.voiculescu.sdjpaspringdataqueries.dao;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ComponentScan(basePackages = "org.voiculescu.sdjpajdbctemplate.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookDaoIntegrationTest {

//    @Autowired
//    BookDao bookDao;
//
//    @Test
//    void testGetBook() {
//        Book byId = bookDao.getById(1L).orElse(null);
//        assertThat(byId).isNotNull();
//    }
//
//    @Test
//    void testGetBookByTitle() {
//        Book book = bookDao.getByTitle("My Book").orElse(null);
//        assertThat(book).isNotNull();
//    }
//
//    @Test
//    void testSaveNewBook() {
//        Author author = new Author().setId(1L);
//        Book book = new Book()
//                .setTitle("Title1")
//                .setIsbn("ISBN1")
//                .setPublisher("Publisher1")
//                .setAuthor(author);
//        AssertionsForClassTypes.assertThat(bookDao.save(book)).isNotNull();
//    }
//
//    @Test
//    void testUpdateBook() {
//        Author author = new Author().setId(1L);
//        Book book = new Book()
//                .setTitle("Title1")
//                .setIsbn("ISBN1")
//                .setPublisher("Publisher1")
//                .setAuthor(author);
//        Book savedBook = bookDao.save(book).orElseThrow(NotFoundException::new);
//        savedBook.setTitle("Test1");
//        Book updatedBook = bookDao.update(savedBook).orElseThrow(NotFoundException::new);
//        assertThat(updatedBook.getTitle()).isEqualTo("Test1");
//    }
//
//    @Test
//    void testDeleteById(){
//        Author author = new Author().setId(1L);
//        Book book = new Book()
//                .setTitle("Title1")
//                .setIsbn("ISBN1")
//                .setPublisher("Publisher1")
//                .setAuthor(author);
//        Book savedBook = bookDao.save(book).orElseThrow(NotFoundException::new);
//        bookDao.deleteById(savedBook.getId());
//        assertThrows(EmptyResultDataAccessException.class,()-> bookDao.getById(savedBook.getId()));
//    }

}
