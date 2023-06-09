package org.voiculescu.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voiculescu.orderservice.entity.OrderApproval;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}