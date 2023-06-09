package org.voiculescu.sdjpaspringdataqueries.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@NamedQueries({
        @NamedQuery(name = "Book.jpaNamed", query = "SELECT b from Book b WHERE b.title ilike concat('%',:title,'%')")
})
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String publisher;
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String title, String publisher, String isbn, Author author) {
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.author = author;
    }
}
