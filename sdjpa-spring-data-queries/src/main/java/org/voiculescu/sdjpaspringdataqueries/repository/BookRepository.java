package org.voiculescu.sdjpaspringdataqueries.repository;

import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.voiculescu.sdjpaspringdataqueries.entity.Book;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> getBookByTitleIgnoreCase(String title);

    Book readByTitle(String title);

    @Nullable
    Book getByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();

    @Async
    Future<Book> queryByTitle(String title);

    @Query("SELECT b from Book b WHERE b.title like %:title%")
    Book findBookByTitleWithQuery(@Param("title") String title);

    @Query(nativeQuery = true, value = "SELECT * FROM book WHERE title like concat('%',:title,'%')")
    Book findBookByTitleWithNativeQuery(@Param("title") String title);

    Book jpaNamed(String title);

}
