package org.voiculescu.sdjpahibernatedao.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.voiculescu.sdjpahibernatedao.entity.Author;
import org.voiculescu.sdjpahibernatedao.exception.NotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ComponentScan(basePackages = "org.voiculescu.sdjpahibernatedao.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testGetAuthor() {
        Author byId = authorDao.getById(1L).orElse(null);
        assertThat(byId).isNotNull();
    }

    @Test
    void testGetAuthorByName() {
        Author byId = authorDao.getByName("First").orElse(null);
        assertThat(byId).isNotNull();
    }

    @Test
    void testSaveNewAuthor() {
        Author author = new Author().setFirstName("Test").setLastName("Test");
        assertThat(authorDao.save(author)).isNotNull();
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author().setFirstName("Test").setLastName("Test");
        Author savedAuthor = authorDao.save(author).orElseThrow(NotFoundException::new);
        savedAuthor.setLastName("Test1");
        Author updatedAuthor = authorDao.update(savedAuthor).orElseThrow(NotFoundException::new);
        assertThat(updatedAuthor.getLastName()).isEqualTo("Test1");
    }

    @Test
    void testDeleteById(){
        Author author = new Author().setFirstName("Test").setLastName("Test");
        Author savedAuthor = authorDao.save(author).orElseThrow(NotFoundException::new);
        authorDao.deleteById(savedAuthor.getId());
        assertThrows(EmptyResultDataAccessException.class,()->authorDao.getById(savedAuthor.getId()));
    }

}
