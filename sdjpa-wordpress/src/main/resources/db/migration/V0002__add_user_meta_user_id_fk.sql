ALTER TABLE wp_usermeta
    ADD CONSTRAINT wp_usermeta_wp_users_ID_fk
        FOREIGN KEY (user_id) REFERENCES wp_users (ID);