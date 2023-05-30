package org.voiculescu.sdjpaintro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.voiculescu.sdjpaintro.domain.AuthorUuid;

import java.util.UUID;

@Repository
public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID> {
}