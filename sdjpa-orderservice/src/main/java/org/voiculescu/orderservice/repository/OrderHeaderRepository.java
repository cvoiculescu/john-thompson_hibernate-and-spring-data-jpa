package org.voiculescu.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.orderservice.entity.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}
