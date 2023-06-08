package org.voiculescu.sdjpa.wordpress.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "wp_comments",
        indexes = {
                @Index(name = "comment_id", columnList = "comment_ID", unique = true),
                @Index(name = "comment_approved_date_gmt", columnList = "comment_approved, comment_date_gmt"),
                @Index(name = "comment_author_email", columnList = "comment_author_email"),
                @Index(name = "comment_date_gmt", columnList = "comment_date_gmt"),
                @Index(name = "comment_parent", columnList = "comment_parent"),
                @Index(name = "comment_post_ID", columnList = "comment_post_ID")
        })
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_ID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "comment_post_ID", nullable = false)
    private Long postId;

    @NotNull
    @Lob
    @Column(name = "comment_author", nullable = false)
    private String author;

    @Size(max = 100)
    @NotNull
    @Column(name = "comment_author_email", nullable = false, length = 100)
    private String authorEmail;

    @Size(max = 200)
    @NotNull
    @Column(name = "comment_author_url", nullable = false, length = 200)
    private String authorUrl;

    @Size(max = 100)
    @NotNull
    @Column(name = "comment_author_IP", nullable = false, length = 100)
    private String authorIp;

    @NotNull
    @Column(name = "comment_date", nullable = false)
    private Timestamp date;

    @NotNull
    @Column(name = "comment_date_gmt", nullable = false)
    private Timestamp dateGmt;

    @NotNull
    @Lob
    @Column(name = "comment_content", nullable = false)
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String content;

    @NotNull
    @Column(name = "comment_karma", nullable = false)
    private Integer karma;

    @Size(max = 20)
    @NotNull
    @Column(name = "comment_approved", nullable = false, length = 20)
    private String approved;

    @Size(max = 255)
    @NotNull
    @Column(name = "comment_agent", nullable = false)
    private String agent;

    @Size(max = 20)
    @NotNull
    @Column(name = "comment_type", nullable = false, length = 20)
    private String type;

    @NotNull
    @Column(name = "comment_parent", nullable = false)
    private Long parent;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "comment")
    private Set<CommentMeta> commentMetaSet = new HashSet<>();

}