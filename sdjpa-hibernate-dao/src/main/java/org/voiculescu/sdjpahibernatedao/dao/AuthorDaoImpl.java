package org.voiculescu.sdjpahibernatedao.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.voiculescu.sdjpahibernatedao.entity.Author;

import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory emf;

    public AuthorDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Optional<Author> getById(Long id) {
        Author author = getEntityManager().find(Author.class, id);
        return Optional.of(author);
    }

    @Override
    public Optional<Author> getByName(String name) {
        EntityManager em = getEntityManager();
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.firstName= :name or a.lastName= :name", Author.class);
        query.setParameter("name",name);
        return Optional.of(query.getSingleResult());
    }

    @Override
    public Optional<Author> save(Author author) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.flush();
        em.getTransaction().commit();
        return Optional.of(author);
    }

    @Override
    public Optional<Author> update(Author savedAuthor) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
