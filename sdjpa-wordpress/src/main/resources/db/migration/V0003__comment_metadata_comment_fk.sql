ALTER TABLE wp_commentmeta
    ADD CONSTRAINT wp_commentmeta_wp_comments_comment_ID_fk
        FOREIGN KEY (comment_id) REFERENCES wp_comments (comment_ID);