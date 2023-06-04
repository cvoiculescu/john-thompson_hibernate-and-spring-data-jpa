package org.voiculescu.sdjpaspringdataqueries.dao;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.sdjpaspringdataqueries.entity.Book;
import org.voiculescu.sdjpaspringdataqueries.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        bookRepository.findAll(PageRequest.of(pageSize, offset));
        return null;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    @Override
    public Book getByTitle(String title) {
        return bookRepository.getBookByTitleIgnoreCase(title).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book update(Book book) {
        Book found = bookRepository.getReferenceById(book.getId());
        found.setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setPublisher(book.getPublisher());
        return bookRepository.save(found);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
