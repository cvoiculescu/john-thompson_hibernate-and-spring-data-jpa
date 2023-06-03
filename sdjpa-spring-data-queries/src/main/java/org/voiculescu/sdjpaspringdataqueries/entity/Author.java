package org.voiculescu.sdjpaspringdataqueries.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@NamedQueries({@NamedQuery(name = "author_find_all", query = "FROM Author"),
        @NamedQuery(name = "author_find_by_name",
                query = "FROM Author WHERE firstName like :name OR lastName like :name")})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
