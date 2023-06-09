package org.voiculescu.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.orderservice.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
