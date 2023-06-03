package org.voiculescu.sdjpaspringdataqueries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.voiculescu.sdjpaspringdataqueries.entity.Book;

import java.util.Optional;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> getBookByTitleIgnoreCase(String title);

    Book readByTitle(String title);

    @Nullable
    Book getByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();

    Book queryByTitle(String title);

    @Query("SELECT b from Book b WHERE b.title like %:title%")
    Book findBookByTitleWithQuery(String title);
}
