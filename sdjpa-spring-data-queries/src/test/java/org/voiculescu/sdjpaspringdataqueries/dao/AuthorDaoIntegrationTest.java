package org.voiculescu.sdjpaspringdataqueries.dao;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.voiculescu.sdjpaspringdataqueries.entity.Author;
import org.voiculescu.sdjpaspringdataqueries.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ComponentScan(basePackages = "org.voiculescu.sdjpaspringdataqueries.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testGetAuthor() {
        assertThat(authorDao.getById(1L)).isNotNull();
    }

    @Test
    void testGetAuthorByName() {
        Author byId = authorDao.getByName("First","Last");
        assertThat(byId).isNotNull();
    }

    @Test
    void testSaveNewAuthor() {
        Author author = new Author().setFirstName("Test").setLastName("Test");
        assertThat(authorDao.save(author).getId()).isNotNull();
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author().setFirstName("Test").setLastName("Test");
        Author savedAuthor = authorDao.save(author);
        savedAuthor.setLastName("Test1");
        Author updatedAuthor = authorDao.update(savedAuthor);
        assertThat(updatedAuthor.getLastName()).isEqualTo("Test1");
    }

    @Test
    void testDeleteById() {
        Author author = new Author().setFirstName("Test").setLastName("Test");
        Author savedAuthor = authorDao.save(author);
        authorDao.deleteById(savedAuthor.getId());
        assertThrows(JpaObjectRetrievalFailureException.class, () -> authorDao.getById(savedAuthor.getId()));
    }

    @Test
    void findAllAuthors() {
        List<Author> authors = authorDao.findAll();
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }
}
