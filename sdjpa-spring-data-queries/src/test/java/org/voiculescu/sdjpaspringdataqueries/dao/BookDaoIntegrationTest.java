package org.voiculescu.sdjpaspringdataqueries.dao;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.voiculescu.sdjpaspringdataqueries.entity.Author;
import org.voiculescu.sdjpaspringdataqueries.entity.Book;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ComponentScan(basePackages = "org.voiculescu.sdjpaspringdataqueries.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookDaoIntegrationTest {

    @Autowired
    BookDao bookDao;

    @Test
    void testGetBook() {
        assertThat(bookDao.getById(1L)).isNotNull();
    }

    @Test
    void testGetBookByTitle() {
        Book book = bookDao.getByTitle("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void testSaveNewBook() {
        Author author = new Author().setId(1L);
        Book book = new Book()
                .setTitle("Title1")
                .setIsbn("ISBN1")
                .setPublisher("Publisher1")
                .setAuthor(author);
        assertThat(bookDao.save(book)).isNotNull();
    }

    @Test
    void testUpdateBook() {
        Author author = new Author().setId(1L);
        Book book = new Book()
                .setTitle("Title1")
                .setIsbn("ISBN1")
                .setPublisher("Publisher1")
                .setAuthor(author);
        Book savedBook = bookDao.save(book);
        savedBook.setTitle("Test1");
        Book updatedBook = bookDao.update(savedBook);
        assertThat(updatedBook.getTitle()).isEqualTo("Test1");
    }

    @Test
    void testDeleteById() {
        Author author = new Author().setId(1L);
        Book book = new Book()
                .setTitle("Title1")
                .setIsbn("ISBN1")
                .setPublisher("Publisher1")
                .setAuthor(author);
        Book savedBook = bookDao.save(book);
        bookDao.deleteById(savedBook.getId());
        assertThrows(JpaObjectRetrievalFailureException.class, () -> bookDao.getById(savedBook.getId()));
    }


}
