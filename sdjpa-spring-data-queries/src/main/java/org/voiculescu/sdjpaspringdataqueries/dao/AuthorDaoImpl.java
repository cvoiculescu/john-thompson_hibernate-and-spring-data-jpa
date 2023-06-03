package org.voiculescu.sdjpaspringdataqueries.dao;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.sdjpaspringdataqueries.entity.Author;
import org.voiculescu.sdjpaspringdataqueries.repository.AuthorRepository;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.getReferenceById(id);
    }

    @Override
    public Author getByName(String fistName, String lastName) {
        return authorRepository.findByFirstNameLikeIgnoreCaseAndLastNameLikeIgnoreCase(fistName, lastName)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author update(Author author) {
        Author found = authorRepository.getReferenceById(author.getId());
        found.setFirstName(author.getFirstName())
                .setLastName(author.getLastName());
        return authorRepository.save(found);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
