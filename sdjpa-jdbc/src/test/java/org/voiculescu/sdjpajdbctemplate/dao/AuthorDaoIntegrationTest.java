package org.voiculescu.sdjpajdbctemplate.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.voiculescu.sdjpajdbctemplate.entity.Author;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ComponentScan(basePackages = "org.voiculescu.sdjpajdbctemplate.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testGetAuthor(){
        Author byId = authorDao.getById(1L).get();
        assertThat(byId).isNotNull();
    }

    @Test
    void testGetAuthorByName(){
        Author byId = authorDao.getByName("Corneliu").get();
        assertThat(byId).isNotNull();
    }

    @Test
    void testSaveNewAuthor(){
        Author author = new Author().setFirstName("Test").setLastName("Test");
        assertThat(authorDao.saveNewAuthor(author)).isNotNull();
    }

    @Test
    void testUpdateAuthor(){
        Author author = new Author().setFirstName("Test").setLastName("Test");
        Author savedAuthor = authorDao.saveNewAuthor(author).orElseThrow(RuntimeException::new);
        savedAuthor.setLastName("Test1");
        Author updatedAuthor = authorDao.updateAuthor(savedAuthor).orElseThrow(RuntimeException::new);
        assertThat(updatedAuthor.getLastName()).isEqualTo("Test1");
    }

}
