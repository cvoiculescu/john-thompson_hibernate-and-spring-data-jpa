package org.voiculescu.sdjpaspringdataqueries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.sdjpaspringdataqueries.entity.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> getBookByTitleIgnoreCase(String title);

}
